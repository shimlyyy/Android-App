package com.example.foodJournal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.foodJournal.R;

public class MainPageActivity extends AppCompatActivity {

    private TextView waterTarget;
    private TextView caloriesDailyBound;
    private TextView waterIntake;
    private TextView caloriesIntake;
    private TextView caloriesBurning;
    private TextView caloriesBalance;

    private Button personalProfile;
    private Button foodManagement;
    private Button waterHistory;
    private Button exerciseHistory;
    private Button foodRecipe;

    //private Search
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        setAllTextViews();
        setAllButtons();

    }

    private void setAllTextViews(){
        waterTarget = (TextView) findViewById(R.id.activity_main_page_waterTarget);
        caloriesDailyBound = (TextView) findViewById(R.id.activity_main_page_caloriesDailyBound);
        waterIntake = (TextView) findViewById(R.id.activity_main_page_waterIntake);
        caloriesIntake = (TextView) findViewById(R.id.activity_main_page_caloriesIntake);
        caloriesBalance = (TextView) findViewById(R.id.activity_main_page_caloriesBalance);
    }

    private void setAllButtons() {
        personalProfile = (Button) findViewById(R.id.activity_main_page_personal_profileBtn);
        personalProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //DO NOTHING
                Intent intent = new Intent(MainPageActivity.this, PersonalProfileActivity.class);
                startActivity(intent);
            }
        });

        foodManagement = (Button) findViewById(R.id.activity_main_page_food_managementBtn);
        foodManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //DO NOTHING
                Intent intent = new Intent(MainPageActivity.this, FoodManagementActivity.class);
                startActivity(intent);
            }
        });

        exerciseHistory = (Button) findViewById(R.id.activity_main_page_exercise_historyBtn);
        exerciseHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //DO NOTHING
                Intent intent = new Intent(MainPageActivity.this, ExerciseHistoryActivity.class);
                startActivity(intent);
            }
        });

        waterHistory = (Button) findViewById(R.id.activity_main_page_water_historyBtn);
        waterHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //DO NOTHING
                Intent intent = new Intent(MainPageActivity.this, WaterHistoryActivity.class);
                startActivity(intent);
            }
        });

        foodRecipe = (Button) findViewById(R.id.activity_main_page_food_recipeBtn);
        foodRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPageActivity.this, FoodRecipeActivity.class);
                startActivity(intent);
            }
        });

//        foodRecipe = (Button) findViewById(R.id.activity_main_page_food_recipeBtn);
//        foodRecipe.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainPageActivity.this, FoodRecipeActivity.class);
//                startActivity(intent);
//            }
//        });
    }
}
