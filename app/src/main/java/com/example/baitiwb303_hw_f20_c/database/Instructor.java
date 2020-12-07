package com.example.baitiwb303_hw_f20_c.database;

import android.provider.BaseColumns;

public final class Instructor {
// To prevent someone from accidentally instantiating the contract class,
// make the constructor private.
private Instructor() {}

/* Inner class that defines the table contents */
public static class InstructorEntry implements BaseColumns {
    public static final String TABLE_NAME = "instructor";
    public static final String COLUMN_NAME_FIRST_NAME = "first_name";
    public static final String COLUMN_NAME_LAST_NAME = "last_name";
    public static final String COLUMN_NAME_GENDER = "gender";
    public static final String COLUMN_NAME_address = "address";
    public static final String COLUMN_NAME_MOBILE_NO = "mobile_no";
}

    public static final String SQL_CREATE_ENTRIES_Instructor =
            "CREATE TABLE " + Instructor.InstructorEntry.TABLE_NAME + " (" +
                    Instructor.InstructorEntry._ID + " INTEGER PRIMARY KEY," +
                    Instructor.InstructorEntry.COLUMN_NAME_FIRST_NAME + " TEXT," +
                    Instructor.InstructorEntry.COLUMN_NAME_LAST_NAME + " TEXT," +
                    Instructor.InstructorEntry.COLUMN_NAME_GENDER + " TEXT," +
                    Instructor.InstructorEntry.COLUMN_NAME_address + " TEXT," +
                    Instructor.InstructorEntry.COLUMN_NAME_MOBILE_NO + " TEXT)";

    public static final String SQL_DELETE_ENTRIES_Instructor =
            "DROP TABLE IF EXISTS " + Instructor.InstructorEntry.TABLE_NAME;
}
