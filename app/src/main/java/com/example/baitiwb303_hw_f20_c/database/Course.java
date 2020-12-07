package com.example.baitiwb303_hw_f20_c.database;

import android.provider.BaseColumns;

public final class Course {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private Course() {}

    /* Inner class that defines the table contents */
    public static class CourseEntry implements BaseColumns {
        public static final String TABLE_NAME = "course";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_HOURS = "hours";
    }

    public static final String SQL_CREATE_ENTRIES_Course =
            "CREATE TABLE " + Course.CourseEntry.TABLE_NAME + " (" +
                    Course.CourseEntry._ID + " INTEGER PRIMARY KEY," +
                    Course.CourseEntry.COLUMN_NAME_TITLE + " TEXT," +
                    Course.CourseEntry.COLUMN_NAME_HOURS + " TEXT)";

    public static final String SQL_DELETE_ENTRIES_Course =
            "DROP TABLE IF EXISTS " + Course.CourseEntry.TABLE_NAME;


}
