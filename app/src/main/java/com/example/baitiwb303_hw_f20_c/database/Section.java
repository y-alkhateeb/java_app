package com.example.baitiwb303_hw_f20_c.database;

import android.provider.BaseColumns;

public final class Section{
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private Section() {}

    /* Inner class that defines the table contents */
    public static class SectionEntry implements BaseColumns {
        public static final String TABLE_NAME = "section";
        public static final String COLUMN_NAME_ROOM_NO = "room_no";
        public static final String COLUMN_NAME_TIME = "time";
    }

    public static final String SQL_CREATE_ENTRIES_Section =
            "CREATE TABLE " + Section.SectionEntry.TABLE_NAME + " (" +
                    Section.SectionEntry._ID + " INTEGER PRIMARY KEY," +
                    Section.SectionEntry.COLUMN_NAME_ROOM_NO + " TEXT," +
                    Section.SectionEntry.COLUMN_NAME_TIME + " TEXT)";

    public static final String SQL_DELETE_ENTRIES_Section =
            "DROP TABLE IF EXISTS " + Section.SectionEntry.TABLE_NAME;
}
