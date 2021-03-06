package com.example.baitiwb303_hw_f20_c.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.example.baitiwb303_hw_f20_c.Models.AccountM;
import com.example.baitiwb303_hw_f20_c.Models.CourseM;
import com.example.baitiwb303_hw_f20_c.Models.EnrollmentM;
import com.example.baitiwb303_hw_f20_c.Models.InstructorM;
import com.example.baitiwb303_hw_f20_c.Models.SectionsM;

import org.json.JSONArray;
import org.json.JSONObject;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "SVU_DB";
    public static final String TABLE_Account = "account";
    public static final String TABLE_COURSE = "course";
    public static final String TABLE_Section = "section";
    public static final String TABLE_SectionsWithInstructorAndCourses = "sectionsWithInstructorAndCourses";
    public static final String TABLE_Instructor = "instructor";
    public static final String TABLE_Enrollment = "enrollment";


    // TABLE Account
    public static final String Column_Account_ID = "account_id";
    public static final String Column_Account_First_Name = "account_first_name";
    public static final String Column_Account_Last_Name = "account_last_name";
    public static final String Column_Account_User_Name = "account_user_name";
    public static final String Column_Account_Password = "account_password";
    public static final String Column_Account_Reg_Yeer = "account_reg_year";
    public static final String Column_Account_Gender = "account_gender";
    public static final String Column_Account_Address = "account_address";
    public static final String Column_Account_Mobile = "account_mobile";
    public static final String Column_Account_Privilege = "account_privilege";


    // TABLE Course
    public static final String Column_Course_ID = "course_id";
    public static final String Column_Course_Tittle = "course_tittle";
    public static final String Column_Course_Hours = "course_hours";


    // TABLE Instructor
    public static final String Column_Instructor_ID = "instructor_id";
    public static final String Column_Instructor_First_Name = "instructor_first_name";
    public static final String Column_Instructor_Last_Name = "instructor_last_name";
    public static final String Column_Instructor_Gender = "instructor_gender";
    public static final String Column_Instructor_Address = "instructor_address";
    public static final String Column_Instructor_Mobile = "instructor_mobile";


    // TABLE Section
    public static final String Column_Section_ID = "section_id";
    public static final String Column_Section_No = "section_section_no";
    public static final String Column_Section_Room_No = "section_room_no";
    public static final String Column_Section_Time = "section_time";


    // TABLE  Sections With Student And Courses
    public static final String Column_Sections_With_Instructor_And_Courses = "sctions_with_instructor_and_courses";


    // TABLE Enrollment
    public static final String Column_Enrollment_ID = "enrollment_id";
    public static final String Column_Enrollment_Grade = "enrollment_grade";


    //Create TABLE Accounts
    String Create_TABLE_Accounts = "CREATE TABLE " + TABLE_Account + " (" + Column_Account_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Column_Account_First_Name + " TEXT," +
            Column_Account_Last_Name + " TEXT," +
            Column_Account_User_Name + " TEXT," +
            Column_Account_Password + " TEXT," +
            Column_Account_Reg_Yeer + " TEXT," +
            Column_Account_Gender + " TEXT," +
            Column_Account_Address + " TEXT," +
            Column_Account_Privilege + " TEXT," +
            Column_Account_Mobile + " TEXT" +
            ")";


    // Create TABLE Courses
    String Create_TABLE_Courses = "CREATE TABLE " + TABLE_COURSE + " (" + Column_Course_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Column_Course_Tittle + " TEXT," +
            Column_Course_Hours + " TEXT" +
            ")";


    // Create TABLE Instructor
    String Create_TABLE_Instructor = "CREATE TABLE " + TABLE_Instructor + " (" + Column_Instructor_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Column_Instructor_First_Name + " TEXT," +
            Column_Instructor_Last_Name + " TEXT," +
            Column_Instructor_Gender + " TEXT," +
            Column_Instructor_Address + " TEXT," +
            Column_Instructor_Mobile + " TEXT" +
            ")";


    // Create TABLE Sections
    String Create_TABLE_Sections = "CREATE TABLE " + TABLE_Section + " (" + Column_Section_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Column_Section_No + " TEXT," +
            Column_Section_Room_No + " TEXT," +
            Column_Section_Time + " TEXT" +
            ")";


    // Create TABLE Sections With Student And Courses
    String Create_TABLE_SectionsWithInstructorAndCourses = "CREATE TABLE " + TABLE_SectionsWithInstructorAndCourses + " (" + Column_Sections_With_Instructor_And_Courses + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Column_Course_ID + " INTEGER," +
            Column_Instructor_ID + " INTEGER," +
            Column_Section_ID + " INTEGER" +
            ")";


    // Create TABLE Enrollment
    String Create_TABLE_Enrollment = "CREATE TABLE " + TABLE_Enrollment + " (" + Column_Enrollment_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Column_Account_ID + " INTEGER," +
            Column_Course_ID + " INTEGER," +
            Column_Section_ID + " INTEGER," +
            Column_Enrollment_Grade + " TEXT" +
            ")";


    public JSONArray get_all_data(String Table_Name) {

        SQLiteDatabase db = this.getWritableDatabase();
        String searchQuery = "select * from " + Table_Name;
        Cursor cursor = db.rawQuery(searchQuery, null);
        JSONArray resultSet = new JSONArray();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            int totalColumn = cursor.getColumnCount();
            JSONObject rowObject = new JSONObject();

            for (int i = 0; i < totalColumn; i++) {
                if (cursor.getColumnName(i) != null) {
                    try {
                        if (cursor.getString(i) != null) {
                            rowObject.put(cursor.getColumnName(i), cursor.getString(i));
                        } else {
                            rowObject.put(cursor.getColumnName(i), "");
                        }
                    } catch (Exception e) {
                        return null;
                    }
                }
            }
            resultSet.put(rowObject);
            cursor.moveToNext();
        }


        cursor.close();
        db.close();
        return resultSet;
    }

    public JSONArray get_all_data_groupby(String Table_Name,String groupBy) {

        SQLiteDatabase db = this.getWritableDatabase();
        String searchQuery = "select * from " + Table_Name + " GROUP BY "+groupBy;
        Cursor cursor = db.rawQuery(searchQuery, null);
        JSONArray resultSet = new JSONArray();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            int totalColumn = cursor.getColumnCount();
            JSONObject rowObject = new JSONObject();

            for (int i = 0; i < totalColumn; i++) {
                if (cursor.getColumnName(i) != null) {
                    try {
                        if (cursor.getString(i) != null) {
                            rowObject.put(cursor.getColumnName(i), cursor.getString(i));
                        } else {
                            rowObject.put(cursor.getColumnName(i), "");
                        }
                    } catch (Exception e) {
                        return null;
                    }
                }
            }
            resultSet.put(rowObject);
            cursor.moveToNext();
        }


        cursor.close();
        db.close();
        return resultSet;
    }

    public JSONArray get_all_data_byId(String Table_Name,String id) {

        SQLiteDatabase db = this.getWritableDatabase();
        String searchQuery = "select * from " + Table_Name + " where "+Column_Account_ID+" = ?";
        Cursor cursor = db.rawQuery(searchQuery, new String[] {id});
        JSONArray resultSet = new JSONArray();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            int totalColumn = cursor.getColumnCount();
            JSONObject rowObject = new JSONObject();

            for (int i = 0; i < totalColumn; i++) {
                if (cursor.getColumnName(i) != null) {
                    try {
                        if (cursor.getString(i) != null) {
                            rowObject.put(cursor.getColumnName(i), cursor.getString(i));
                        } else {
                            rowObject.put(cursor.getColumnName(i), "");
                        }
                    } catch (Exception e) {
                        return null;
                    }
                }
            }
            resultSet.put(rowObject);
            cursor.moveToNext();
        }


        cursor.close();
        db.close();
        return resultSet;
    }

    public JSONArray get_all_course_instructor_by_id(String Table_Name,String id) {

        SQLiteDatabase db = this.getWritableDatabase();
        String searchQuery = "select * from " + Table_Name + " where "+Column_Section_ID+" = ?";
        Cursor cursor = db.rawQuery(searchQuery, new String[] {id});
        JSONArray resultSet = new JSONArray();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            int totalColumn = cursor.getColumnCount();
            JSONObject rowObject = new JSONObject();

            for (int i = 0; i < totalColumn; i++) {
                if (cursor.getColumnName(i) != null) {
                    try {
                        if (cursor.getString(i) != null) {
                            rowObject.put(cursor.getColumnName(i), cursor.getString(i));
                        } else {
                            rowObject.put(cursor.getColumnName(i), "");
                        }
                    } catch (Exception e) {
                        return null;
                    }
                }
            }
            resultSet.put(rowObject);
            cursor.moveToNext();
        }


        cursor.close();
        db.close();
        return resultSet;
    }

    public EnrollmentM CreateEnrollment(EnrollmentM data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        try {
            long result = -1;
            contentValues.put(Column_Account_ID, data.getAccount_id());
            contentValues.put(Column_Course_ID, data.getCourse_id());
            contentValues.put(Column_Section_ID, data.getSection_id());
            contentValues.put(Column_Enrollment_Grade, data.getEnrollment_grade());

            result = db.insert(TABLE_Enrollment, null, contentValues);


            if (result == -1) {
                db.close();
                Log.i("InsertIntoAccount", "InsertIntoAccount: false");
                return null;
            } else {
                db.close();
                Log.i("InsertIntoAccount", "InsertIntoAccount: true");
                return data;
            }
        } catch (Exception e) {
            Log.i("InsertIntoAccount", "Exception : " + e.getMessage());
            return null;
        }


    }

    public JSONArray getSectionByName(String Table_Name) {

        SQLiteDatabase db = this.getWritableDatabase();
        String searchQuery = "select * from " + Table_Name + " group by " + Column_Section_No;
        Cursor cursor = db.rawQuery(searchQuery, null);
        JSONArray resultSet = new JSONArray();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            int totalColumn = cursor.getColumnCount();
            JSONObject rowObject = new JSONObject();

            for (int i = 0; i < totalColumn; i++) {
                if (cursor.getColumnName(i) != null) {
                    try {
                        if (cursor.getString(i) != null) {
                            rowObject.put(cursor.getColumnName(i), cursor.getString(i));
                        } else {
                            rowObject.put(cursor.getColumnName(i), "");
                        }
                    } catch (Exception e) {
                        return null;
                    }
                }
            }
            resultSet.put(rowObject);
            cursor.moveToNext();
        }


        cursor.close();
        db.close();
        return resultSet;
    }

    public JSONArray SignIn(String UserName, String Password) {

        SQLiteDatabase db = this.getWritableDatabase();
        String searchQuery;
        searchQuery = "select * from " + "account" + " where account_user_name " + " = '" + UserName + "'" + " and " + " account_password " + " = '" + Password + "';";

        Cursor cursor = db.rawQuery(searchQuery, null);
        JSONArray resultSet = new JSONArray();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            int totalColumn = cursor.getColumnCount();
            JSONObject rowObject = new JSONObject();

            for (int i = 0; i < totalColumn; i++) {
                if (cursor.getColumnName(i) != null) {
                    try {
                        if (cursor.getString(i) != null) {
                            rowObject.put(cursor.getColumnName(i), cursor.getString(i));
                        } else {
                            rowObject.put(cursor.getColumnName(i), "");
                        }
                    } catch (Exception e) {
                        return null;
                    }
                }
            }
            resultSet.put(rowObject);
            cursor.moveToNext();
        }


        cursor.close();
        db.close();
        return resultSet;
    }

    public AccountM SignUp(AccountM data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        try {
            long result = -1;
            contentValues.put(Column_Account_First_Name, data.getFirst_name());
            contentValues.put(Column_Account_Last_Name, data.getLast_name());
            contentValues.put(Column_Account_User_Name, data.getUser_name());
            contentValues.put(Column_Account_Password, data.getPassword());
            contentValues.put(Column_Account_Reg_Yeer, data.getReg_Year());
            contentValues.put(Column_Account_Gender, data.getGender());
            contentValues.put(Column_Account_Address, data.getAddress());
            contentValues.put(Column_Account_Mobile, data.getMobile_No());
            contentValues.put(Column_Account_Privilege, data.getPrivilege());

            result = db.insert(TABLE_Account, null, contentValues);


            if (result == -1) {
                db.close();
                Log.i("InsertIntoAccount", "InsertIntoAccount: false");
                return null;
            } else {
                db.close();
                Log.i("InsertIntoAccount", "InsertIntoAccount: true");
                return data;
            }
        } catch (Exception e) {
            Log.i("InsertIntoAccount", "Exception : " + e.getMessage());
            return null;
        }


    }

    public AccountM updateAccount(AccountM data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        try {
            long result = -1;
            contentValues.put(Column_Account_First_Name, data.getFirst_name());
            contentValues.put(Column_Account_Last_Name, data.getLast_name());
            contentValues.put(Column_Account_User_Name, data.getUser_name());
            contentValues.put(Column_Account_Password, data.getPassword());
            contentValues.put(Column_Account_Reg_Yeer, data.getReg_Year());
            contentValues.put(Column_Account_Gender, data.getGender());
            contentValues.put(Column_Account_Address, data.getAddress());
            contentValues.put(Column_Account_Mobile, data.getMobile_No());
            contentValues.put(Column_Account_Privilege, data.getPrivilege());

            result = db.update(TABLE_Account, contentValues, Column_Account_ID + "=?", new String[]{data.getAccount_id()});

            if (result == -1) {
                db.close();
                Log.i("InsertIntoAccount", "InsertIntoAccount: false");
                return null;
            } else {
                db.close();
                Log.i("InsertIntoAccount", "InsertIntoAccount: true");
                return data;
            }
        } catch (Exception e) {
            Log.i("InsertIntoAccount", "Exception : " + e.getMessage());
            return null;
        }


    }

    public CourseM CreateCourse(CourseM data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        try {
            long result = -1;
            contentValues.put(Column_Course_Tittle, data.getCourse_tittle());
            contentValues.put(Column_Course_Hours, data.getCourse_hours());

            result = db.insert(TABLE_COURSE, null, contentValues);


            if (result == -1) {
                db.close();
                Log.i("InsertIntoAccount", "InsertIntoAccount: false");
                return null;
            } else {
                db.close();
                Log.i("InsertIntoAccount", "InsertIntoAccount: true");
                return data;
            }
        } catch (Exception e) {
            Log.i("InsertIntoAccount", "Exception : " + e.getMessage());
            return null;
        }


    }

    public CourseM updateCourse(CourseM data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        try {
            long result = -1;
            contentValues.put(Column_Course_Tittle, data.getCourse_tittle());
            contentValues.put(Column_Course_Hours, data.getCourse_hours());

            result = db.update(TABLE_COURSE, contentValues, Column_Course_ID + "=?", new String[]{data.getCourse_id()});

            if (result == -1) {
                db.close();
                Log.i("InsertIntoAccount", "InsertIntoAccount: false");
                return null;
            } else {
                db.close();
                Log.i("InsertIntoAccount", "InsertIntoAccount: true");
                return data;
            }
        } catch (Exception e) {
            Log.i("InsertIntoAccount", "Exception : " + e.getMessage());
            return null;
        }


    }

    public InstructorM CreateInstructor(InstructorM data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        try {
            long result = -1;
            contentValues.put(Column_Instructor_First_Name, data.getInstructor_first_name());
            contentValues.put(Column_Instructor_Last_Name, data.getInstructor_last_name());
            contentValues.put(Column_Instructor_Gender, data.getInstructor_gender());
            contentValues.put(Column_Instructor_Address, data.getInstructor_address());
            contentValues.put(Column_Instructor_Mobile, data.getInstructor_mobile());

            result = db.insert(TABLE_Instructor, null, contentValues);


            if (result == -1) {
                db.close();
                Log.i("InsertIntoAccount", "InsertIntoAccount: false");
                return null;
            } else {
                db.close();
                Log.i("InsertIntoAccount", "InsertIntoAccount: true");
                return data;
            }
        } catch (Exception e) {
            Log.i("InsertIntoAccount", "Exception : " + e.getMessage());
            return null;
        }


    }

    public InstructorM updateInstructor(InstructorM data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        try {
            long result = -1;
            contentValues.put(Column_Instructor_First_Name, data.getInstructor_first_name());
            contentValues.put(Column_Instructor_Last_Name, data.getInstructor_last_name());
            contentValues.put(Column_Instructor_Gender, data.getInstructor_gender());
            contentValues.put(Column_Instructor_Address, data.getInstructor_address());
            contentValues.put(Column_Instructor_Mobile, data.getInstructor_mobile());

            result = db.update(TABLE_Instructor, contentValues, Column_Instructor_ID + "=?", new String[]{data.getInstructor_id()});


            if (result == -1) {
                db.close();
                Log.i("InsertIntoAccount", "InsertIntoAccount: false");
                return null;
            } else {
                db.close();
                Log.i("InsertIntoAccount", "InsertIntoAccount: true");
                return data;
            }
        } catch (Exception e) {
            Log.i("InsertIntoAccount", "Exception : " + e.getMessage());
            return null;
        }


    }

    public SectionsM CreateSection(SectionsM data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        try {
            long result = -1;
            contentValues.put(Column_Section_Room_No, data.getSection_room_no());
            contentValues.put(Column_Section_Time, data.getSection_time());
            contentValues.put(Column_Section_No, data.getSection_section_no());

            result = db.insert(TABLE_Section, null, contentValues);


            if (result == -1) {
                db.close();
                Log.i("InsertIntoAccount", "InsertIntoAccount: false");
                return null;
            } else {
                db.close();
                Log.i("InsertIntoAccount", "InsertIntoAccount: true");
                return data;
            }
        } catch (Exception e) {
            Log.i("InsertIntoAccount", "Exception : " + e.getMessage());
            return null;
        }


    }

    public void updateSection(SectionsM data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        try {
            long result = -1;
            contentValues.put(Column_Section_Room_No, data.getSection_room_no());
            contentValues.put(Column_Section_Time, data.getSection_time());
            contentValues.put(Column_Section_No, data.getSection_section_no());

            result = db.update(TABLE_Section, contentValues, Column_Section_ID + "=?", new String[]{data.getSection_id()});


            if (result == -1) {
                db.close();
                Log.i("InsertIntoAccount", "InsertIntoAccount: false");
            } else {
                db.close();
                Log.i("InsertIntoAccount", "InsertIntoAccount: true");
            }
        } catch (Exception e) {
            Log.i("InsertIntoAccount", "Exception : " + e.getMessage());
        }


    }

    public SectionsM addCourseAndInstructorToSection(SectionsM data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        try {
            long result = -1;
            contentValues.put(Column_Section_ID, data.getSection_id());
            contentValues.put(Column_Course_ID, data.getCourse_id());
            contentValues.put(Column_Instructor_ID, data.getInstructor_id());

            result = db.insert(TABLE_SectionsWithInstructorAndCourses, null, contentValues);


            if (result == -1) {
                db.close();
                Log.i("InsertIntoAccount", "InsertIntoAccount: false");
                return null;
            } else {
                db.close();
                Log.i("InsertIntoAccount", "InsertIntoAccount: true");
                return data;
            }
        } catch (Exception e) {
            Log.i("InsertIntoAccount", "Exception : " + e.getMessage());
            return null;
        }


    }

    public void updateCourseAndInstructorToSection(SectionsM data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        try {
            long result = -1;
            contentValues.put(Column_Course_ID, Integer.parseInt(data.getCourse_id()));
            contentValues.put(Column_Instructor_ID, Integer.parseInt(data.getInstructor_id()));

            result = db.update(TABLE_SectionsWithInstructorAndCourses, contentValues, Column_Section_ID + "=?", new String[]{data.getSection_id()});


            if (result == -1) {
                db.close();
                Log.i("InsertIntoAccount", "InsertIntoAccount: false");
            } else {
                db.close();
                Log.i("InsertIntoAccount", "InsertIntoAccount: true");
            }
        } catch (Exception e) {
            Log.i("InsertIntoAccount", "Exception : " + e.getMessage());
        }


    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Create_TABLE_Accounts);
        db.execSQL(Create_TABLE_Courses);
        db.execSQL(Create_TABLE_Sections);
        db.execSQL(Create_TABLE_SectionsWithInstructorAndCourses);
        db.execSQL(Create_TABLE_Instructor);
        db.execSQL(Create_TABLE_Enrollment);
        ContentValues contentValues = new ContentValues();
        contentValues.put(Column_Account_First_Name, "Ahmad");
        contentValues.put(Column_Account_Last_Name, "Rashdan");
        contentValues.put(Column_Account_User_Name, "88314");
        contentValues.put(Column_Account_Password, "12345678");
        contentValues.put(Column_Account_Reg_Yeer, "2020");
        contentValues.put(Column_Account_Gender, "Male");
        contentValues.put(Column_Account_Address, "Damascus");
        contentValues.put(Column_Account_Mobile, "0987654321");
        contentValues.put(Column_Account_Privilege, "1");
        db.insert(TABLE_Account, null, contentValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Account);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Section);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Instructor);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Enrollment);
        onCreate(db);

    }

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public boolean DeleteDB(Context context) {

        boolean isDeleted = context.deleteDatabase(DATABASE_NAME);
        return isDeleted;
    }

    public boolean isEmpty(String TableName) {

        SQLiteDatabase database = this.getReadableDatabase();
        int NoOfRows = (int) DatabaseUtils.queryNumEntries(database, TableName);

        if (NoOfRows == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void DeleteDataInTable(String TABLE_NAME) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
    }

    public void DeleteRawInTable(String TABLE_NAME, String key, String ID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, key + "=?", new String[]{ID});
    }


}
