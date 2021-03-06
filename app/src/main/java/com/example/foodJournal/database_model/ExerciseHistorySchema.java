package com.example.foodJournal.database_model;

import android.provider.BaseColumns;

public class ExerciseHistorySchema {

    private ExerciseHistorySchema() {}

    //implement BaseColumns which inherit a primary key field called _ID that
    //some android classes such as cursor adaptors will expect it to have
    public static final class ExerciseHistoryEntry implements BaseColumns {
        public static final String TABLE_NAME = "ExerciseHistoryList";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_AMOUMT = "amount";
        public static final String COLUMN_CALORIE = "calorie";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }
}