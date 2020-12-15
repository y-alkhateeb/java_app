package com.example.baitiwb303_hw_f20_c.activity.ui.course;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.baitiwb303_hw_f20_c.Models.CourseM;
import com.example.baitiwb303_hw_f20_c.database.DatabaseHelper;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import static com.example.baitiwb303_hw_f20_c.database.DatabaseHelper.Column_Course_Hours;
import static com.example.baitiwb303_hw_f20_c.database.DatabaseHelper.Column_Course_Tittle;


public class CourseViewModel extends AndroidViewModel {

    private final MutableLiveData<List<CourseM>> mAllCourse;
    private  final List<CourseM> courseMList;
    private DatabaseHelper databaseHelper;
    private JSONArray jsonArray;;

    public CourseViewModel(@NonNull Application application) {
        super(application);
        mAllCourse = new MutableLiveData<>();
        courseMList = new ArrayList<>();
        databaseHelper = new DatabaseHelper(application.getApplicationContext());
        jsonArray = databaseHelper.get_all_data("course");
        if (jsonArray.length() > 0) {
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    CourseM courseM = new CourseM();
                    courseM.setCourse_tittle(jsonArray.getJSONObject(i).getString(Column_Course_Tittle));
                    courseM.setCourse_hours(jsonArray.getJSONObject(i).getString(Column_Course_Hours));
                    courseMList.add(courseM);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            mAllCourse.setValue(courseMList);
        }
    }

    public LiveData<List<CourseM>> getCourse() {
        return mAllCourse;
    }

    public boolean createCourse(CourseM courseM){
        return databaseHelper.CreateCourse(courseM) != null;
    }
}