package com.example.baitiwb303_hw_f20_c.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "SVU_DB";
    private static final String TABLE_Student = "student";
    private static final String TABLE_COURSE = "course";
    private static final String TABLE_Section = "section";
    private static final String TABLE_Instructor = "instructor";
    private static final String TABLE_Enrollment = "enrollment";


    // TABLE Student
    private static final String Column_Student_ID = "student_id";
    private static final String Column_Student_First_Name = "student_first_name";
    private static final String Column_Student_Last_Name = "student_last_name";
    private static final String Column_Student_User_Name = "student_user_name";
    private static final String Column_Student_Password = "student_password";
    private static final String Column_Student_Reg_Yeer = "student_reg_year";
    private static final String Column_Student_Gender = "student_gender";
    private static final String Column_Student_Address = "student_address";
    private static final String Column_Student_Mobile = "student_mobile";


    // TABLE Course
    private static final String Column_Course_ID = "course_id";
    private static final String Column_Course_Tittle = "course_tittle";
    private static final String Column_Course_Hours = "course_hours";


    // TABLE Instructor
    private static final String Column_Instructor_ID = "instructor_id";
    private static final String Column_Instructor_First_Name = "instructor_first_name";
    private static final String Column_Instructor_Last_Name = "instructor_last_name";
    private static final String Column_Instructor_Gender = "instructor_gender";
    private static final String Column_Instructor_Address = "instructor_address";
    private static final String Column_Instructor_Mobile = "instructor_mobile";


    // TABLE Section
    private static final String Column_Section_ID = "section_id";
    private static final String Column_Section_No = "section_section_no";
    private static final String Column_Section_Room_No = "section_room_no";
    private static final String Column_Section_Time = "section_time";

    // TABLE Enrollment
    private static final String Column_Enrollment_ID = "enrollment_id";
    private static final String Column_Enrollment_Grade = "enrollment_grade";


    //Create TABLE Students
    String Create_TABLE_Students = "CREATE TABLE " + TABLE_Student + " (" + Column_Student_ID + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Column_Student_First_Name + " TEXT," +
            Column_Student_Last_Name + " TEXT," +
            Column_Student_User_Name + " TEXT," +
            Column_Student_Password + " TEXT," +
            Column_Student_Reg_Yeer + " TEXT," +
            Column_Student_Gender + " TEXT," +
            Column_Student_Address + " TEXT," +
            Column_Student_Mobile + " TEXT" +
            ")";


    // Create TABLE Courses
    String Create_TABLE_Courses = "CREATE TABLE " + TABLE_COURSE + " (" + Column_Course_ID + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Column_Course_Tittle + " TEXT," +
            Column_Course_Hours + " TEXT" +
            ")";


    // Create TABLE Instructor
    String Create_TABLE_Instructor = "CREATE TABLE " + TABLE_Instructor + " (" + Column_Instructor_ID + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Column_Instructor_First_Name + " TEXT," +
            Column_Instructor_Last_Name + " TEXT," +
            Column_Instructor_Gender + " TEXT," +
            Column_Instructor_Address + " TEXT," +
            Column_Instructor_Mobile + " TEXT" +
            ")";


    // Create TABLE Sections
    String Create_TABLE_Sections = "CREATE TABLE " + TABLE_Section + " (" + Column_Section_ID + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Column_Course_ID + " INTEGER," +
            Column_Instructor_ID + " INTEGER," +
            Column_Section_No + " TEXT," +
            Column_Section_Room_No + " TEXT," +
            Column_Section_Time + " TEXT" +
            ")";


    // Create TABLE Enrollment
    String Create_TABLE_Enrollment = "CREATE TABLE " + TABLE_Enrollment + " (" + Column_Enrollment_ID + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
            Column_Student_ID + " INTEGER," +
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

    public JSONArray GetDataWithCondition(String Table_Name, String IdDownloadVisit, String IDTasks) {

        SQLiteDatabase db = this.getWritableDatabase();
        String searchQuery;
        if (IDTasks == null) {
            searchQuery = "select * from " + Table_Name + " where id_dawnload_visits " + " = '" + IdDownloadVisit + "';";
        } else {
            searchQuery = "select * from " + Table_Name + " where id_dawnload_visits " + " = '" + IdDownloadVisit + "'" + " and " + " id_tasks " + " = '" + IDTasks + "';";

        }
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

    public JSONArray GetDataQuestionUpload(String Table_Name, String VisitID) {

        SQLiteDatabase db = this.getWritableDatabase();
        String searchQuery;
        searchQuery = "select * from " + Table_Name + " where visit_id " + " = '" + VisitID + "';";

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

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Create_TABLE_Students);
        db.execSQL(Create_TABLE_Courses);
        db.execSQL(Create_TABLE_Sections);
        db.execSQL(Create_TABLE_Instructor);
        db.execSQL(Create_TABLE_Enrollment);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Student);
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

    public void DeleteRawInTable(String TABLE_NAME, String IDDawnloadVisits) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "id_dawnload_visits=?", new String[]{IDDawnloadVisits});
    }


}
