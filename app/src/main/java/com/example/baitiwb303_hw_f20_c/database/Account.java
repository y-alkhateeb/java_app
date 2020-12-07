package com.example.baitiwb303_hw_f20_c.database;

import android.provider.BaseColumns;

public final class Account {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private Account() {}

    /* Inner class that defines the table contents */
    public static class AccountEntry implements BaseColumns {
        public static final String TABLE_NAME = "account";
        public static final String COLUMN_NAME_PRIVILEGE = "privilege";
        public static final String COLUMN_NAME_FIRST_NAME = "first_name";
        public static final String COLUMN_NAME_LAST_NAME = "last_name";
        public static final String COLUMN_NAME_USER_NAME = "user_name";
        public static final String COLUMN_NAME_PASSWORD = "password";
        public static final String COLUMN_NAME_REF_YEAR = "reg_Year";
        public static final String COLUMN_NAME_gender = "gender";
        public static final String COLUMN_NAME_ADDRESS = "address";
        public static final String COLUMN_NAME_MOBILE_NO = "mobile_No";
    }
    public static final String SQL_CREATE_ENTRIES_Account =
            "CREATE TABLE " + Account.AccountEntry.TABLE_NAME + " (" +
                    Account.AccountEntry._ID + " INTEGER PRIMARY KEY," +
                    Account.AccountEntry.COLUMN_NAME_PRIVILEGE + " TEXT," +
                    Account.AccountEntry.COLUMN_NAME_FIRST_NAME + " TEXT," +
                    Account.AccountEntry.COLUMN_NAME_LAST_NAME + " TEXT," +
                    Account.AccountEntry.COLUMN_NAME_USER_NAME + " TEXT," +
                    Account.AccountEntry.COLUMN_NAME_PASSWORD + " TEXT," +
                    Account.AccountEntry.COLUMN_NAME_REF_YEAR + " TEXT," +
                    Account.AccountEntry.COLUMN_NAME_gender + " TEXT," +
                    Account.AccountEntry.COLUMN_NAME_ADDRESS + " TEXT," +
                    Account.AccountEntry.COLUMN_NAME_MOBILE_NO + " TEXT)";

    public static final String SQL_DELETE_ENTRIES_Account =
            "DROP TABLE IF EXISTS " + Account.AccountEntry.TABLE_NAME;
}
