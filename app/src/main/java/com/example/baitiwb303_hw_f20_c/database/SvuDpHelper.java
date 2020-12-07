package com.example.baitiwb303_hw_f20_c.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.baitiwb303_hw_f20_c.database.Account.SQL_CREATE_ENTRIES_Account;
import static com.example.baitiwb303_hw_f20_c.database.Account.SQL_DELETE_ENTRIES_Account;
import static com.example.baitiwb303_hw_f20_c.database.Course.SQL_CREATE_ENTRIES_Course;
import static com.example.baitiwb303_hw_f20_c.database.Course.SQL_DELETE_ENTRIES_Course;
import static com.example.baitiwb303_hw_f20_c.database.Enrollment.SQL_CREATE_ENTRIES_Enrollment;
import static com.example.baitiwb303_hw_f20_c.database.Enrollment.SQL_DELETE_ENTRIES_Enrollment;
import static com.example.baitiwb303_hw_f20_c.database.Instructor.SQL_CREATE_ENTRIES_Instructor;
import static com.example.baitiwb303_hw_f20_c.database.Instructor.SQL_DELETE_ENTRIES_Instructor;
import static com.example.baitiwb303_hw_f20_c.database.Section.SQL_CREATE_ENTRIES_Section;
import static com.example.baitiwb303_hw_f20_c.database.Section.SQL_DELETE_ENTRIES_Section;

public class SvuDpHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SvuDb.db";

    public SvuDpHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES_Account);
        db.execSQL(SQL_CREATE_ENTRIES_Course);
        db.execSQL(SQL_CREATE_ENTRIES_Enrollment);
        db.execSQL(SQL_CREATE_ENTRIES_Section);
        db.execSQL(SQL_CREATE_ENTRIES_Instructor);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES_Account);
        db.execSQL(SQL_DELETE_ENTRIES_Course);
        db.execSQL(SQL_DELETE_ENTRIES_Section);
        db.execSQL(SQL_DELETE_ENTRIES_Enrollment);
        db.execSQL(SQL_DELETE_ENTRIES_Instructor);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
