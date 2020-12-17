package com.example.baitiwb303_hw_f20_c.activity.ui.section;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.baitiwb303_hw_f20_c.Models.SectionsM;
import com.example.baitiwb303_hw_f20_c.database.DatabaseHelper;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import static com.example.baitiwb303_hw_f20_c.database.DatabaseHelper.Column_Course_ID;
import static com.example.baitiwb303_hw_f20_c.database.DatabaseHelper.Column_Instructor_ID;
import static com.example.baitiwb303_hw_f20_c.database.DatabaseHelper.Column_Section_ID;
import static com.example.baitiwb303_hw_f20_c.database.DatabaseHelper.Column_Section_No;
import static com.example.baitiwb303_hw_f20_c.database.DatabaseHelper.Column_Section_Room_No;
import static com.example.baitiwb303_hw_f20_c.database.DatabaseHelper.Column_Section_Time;
import static com.example.baitiwb303_hw_f20_c.database.DatabaseHelper.Column_Sections_With_Instructor_And_Courses;

public class CourseAndInstructorViewModel extends AndroidViewModel {
    private final MutableLiveData<List<SectionsM>> mAllCourseAndInstructor;
    private final List<SectionsM> courseAndInstructorMList;
    private final DatabaseHelper databaseHelper;

    public CourseAndInstructorViewModel(@NonNull Application application) {
        super(application);
        mAllCourseAndInstructor = new MutableLiveData<>();
        courseAndInstructorMList = new ArrayList<>();
        databaseHelper = new DatabaseHelper(application.getApplicationContext());
    }


    public LiveData<List<SectionsM>> getCourseAndInstructor(String id) {
        JSONArray jsonArray = databaseHelper.get_all_course_instructor_by_id("sectionsWithInstructorAndCourses",id);
        if (jsonArray.length() > 0) {
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    SectionsM sectionsM = new SectionsM();
                    sectionsM.setSection_id(jsonArray.getJSONObject(i).getString(Column_Section_ID));
                    sectionsM.setCourse_id(jsonArray.getJSONObject(i).getString(Column_Course_ID));
                    sectionsM.setInstructor_id(jsonArray.getJSONObject(i).getString(Column_Instructor_ID));
                    courseAndInstructorMList.add(sectionsM);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            mAllCourseAndInstructor.setValue(courseAndInstructorMList);
        }
        return mAllCourseAndInstructor;
    }


    public void delete(String id){
        databaseHelper.DeleteRawInTable("sectionsWithInstructorAndCourses",Column_Sections_With_Instructor_And_Courses,id);
    }
}