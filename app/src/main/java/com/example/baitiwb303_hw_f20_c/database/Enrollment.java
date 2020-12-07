package com.example.baitiwb303_hw_f20_c.database;

import android.provider.BaseColumns;

public final class Enrollment {
    // To prevent someone from accidentally instantiating the contract class,
// make the constructor private.
    private Enrollment() {}

    /* Inner class that defines the table contents */
    public static class EnrollmentEntry implements BaseColumns {
        public static final String TABLE_NAME = "enrollment";
        public static final String COLUMN_NAME_GRADE = "grade";
    }

    public static final String SQL_CREATE_ENTRIES_Enrollment =
            "CREATE TABLE " + Enrollment.EnrollmentEntry.TABLE_NAME + " (" +
                    Enrollment.EnrollmentEntry._ID + " INTEGER PRIMARY KEY," +
                    Enrollment.EnrollmentEntry.COLUMN_NAME_GRADE + " TEXT)";

    public static final String SQL_DELETE_ENTRIES_Enrollment =
            "DROP TABLE IF EXISTS " + Enrollment.EnrollmentEntry.TABLE_NAME;
}
