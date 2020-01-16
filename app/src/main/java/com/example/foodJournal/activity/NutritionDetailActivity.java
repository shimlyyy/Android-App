package com.example.foodJournal.activity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodJournal.R;
import com.example.foodJournal.adapter.FoodHistoryAdapter;
import com.example.foodJournal.database_model.FoodHistoryDBHelper;
import com.example.foodJournal.database_model.FoodHistorySchema;
import com.example.foodJournal.db_extractor.USDA_Nutrition_Extraction;

import java.sql.Timestamp;


/**
 * Overview:
 *
 * Creates the layout for the nutrition intent
 * Displays the nutrition extracted and prints it onto the screen
 * Has a back and add button
 *    -> back button brings the intent back to the FoodManagementActivity intent (search and display food item screen)
 *    -> add button adds the nutrition into ___ intent
 */
public class NutritionDetailActivity extends AppCompatActivity {

    public static TextView nutrition_food_name;
    public static TextView nutrition_calories;
    public static TextView nutrition_protein;
    public static TextView nutrition_sodium;
    public static TextView nutrition_fat;

    public static Context context;
    public static String ndbno;

    private SQLiteDatabase mDatabase;
    private FoodHistoryAdapter mAdapter;
    private EditText mAmount;

    private Button back_button;
    private Button add_button;

    /**
     * Creates a TextView with nutritions on it
     * Creates a add button
     * Creates a back button
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_detail);

        context = this;
        nutrition_food_name = (TextView) findViewById(R.id.nutrition_food_name);
        nutrition_calories = (TextView) findViewById(R.id.nutrition_food_calories);
        nutrition_protein = (TextView) findViewById(R.id.nutrition_food_protein);
        nutrition_sodium = (TextView) findViewById(R.id.nutrition_food_sodium);
        nutrition_fat = (TextView) findViewById(R.id.nutrition_food_fat);
        /**
         * Creates an USDA_Nutrition_Extraction class to retrieve nutrition data in a separate thread
         */



        final USDA_Nutrition_Extraction usda = new USDA_Nutrition_Extraction(ndbno);
        usda.execute();//AsyncTask built-in function to copy info from search thread to main thread
        //info is in usda now, we could use getter

        /**
         * Initiates back button to return back to the search intent
         */

        FoodHistoryDBHelper dbHelper = new FoodHistoryDBHelper(this);
        mDatabase = dbHelper.getWritableDatabase();
        mAmount = (EditText) findViewById(R.id.nutrition_add_amount);

        back_button = (Button) findViewById(R.id.nutrition_backBtn);
        back_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FoodManagementActivity.context, FoodManagementActivity.class);
                FoodManagementActivity.searchbar.setText(FoodManagementActivity.search);
                FoodManagementActivity.food_spinner.setSelection(FoodManagementActivity.spinner_pos);
                FoodManagementActivity.enter_button.performClick();
                FoodManagementActivity.scrollView.setScrollY(FoodManagementActivity.scroll_pos);
                startActivity(intent);
            }
        });

        add_button = (Button) findViewById(R.id.nutrition_addBtn);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String unit_calorie = nutrition_calories.getText().toString().replaceAll("\\D+","");
                if (unit_calorie.equals("")) {
                    Toast.makeText(getApplicationContext(),"No energy information available " +
                            "for this food, so you can't add it to the history",Toast.LENGTH_SHORT).show();
                } else {
                    String name = nutrition_food_name.getText().toString();
                    String timestamp = String.valueOf(new Timestamp(System.currentTimeMillis()));
                    float amount = Float.valueOf(mAmount.getText().toString());
                    float calories = amount * Float.valueOf(unit_calorie);
                    ContentValues cv = new ContentValues();
                    cv.put(FoodHistorySchema.FoodHistoryEntry.COLUMN_NAME, name);
                    cv.put(FoodHistorySchema.FoodHistoryEntry.COLUMN_AMOUMT, amount);
                    cv.put(FoodHistorySchema.FoodHistoryEntry.COLUMN_CALORIE, calories);
                    cv.put(FoodHistorySchema.FoodHistoryEntry.COLUMN_TIMESTAMP, timestamp);
                    mDatabase.insert(FoodHistorySchema.FoodHistoryEntry.TABLE_NAME, null, cv);
                    Intent intent = new Intent(NutritionDetailActivity.context, FoodHistoryActivity.class);
                    startActivity(intent);
                }

            }
        });
    }
}
