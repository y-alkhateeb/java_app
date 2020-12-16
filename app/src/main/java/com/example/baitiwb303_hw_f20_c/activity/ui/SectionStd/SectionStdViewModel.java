package com.example.baitiwb303_hw_f20_c.activity.ui.SectionStd;

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

public class SectionStdViewModel extends AndroidViewModel {
    private final MutableLiveData<List<SectionsM>> mAllSection;
    private  final List<SectionsM> sectionsMList;
    private final DatabaseHelper databaseHelper;
    private final JSONArray jsonArray;

    public SectionStdViewModel(@NonNull Application application) {
        super(application);
        mAllSection = new MutableLiveData<>();
        sectionsMList = new ArrayList<>();
        databaseHelper = new DatabaseHelper(application.getApplicationContext());
        jsonArray = databaseHelper.getSectionByName("section");
        if (jsonArray.length() > 0) {
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    SectionsM sectionsM = new SectionsM();
                    sectionsM.setSection_id(jsonArray.getJSONObject(i).getString(Column_Section_ID));
                    sectionsM.setCourse_id(jsonArray.getJSONObject(i).getString(Column_Course_ID));
                    sectionsM.setInstructor_id(jsonArray.getJSONObject(i).getString(Column_Instructor_ID));
                    sectionsM.setSection_room_no(jsonArray.getJSONObject(i).getString(Column_Section_Room_No));
                    sectionsM.setSection_time(jsonArray.getJSONObject(i).getString(Column_Section_Time));
                    sectionsM.setSection_section_no(jsonArray.getJSONObject(i).getString(Column_Section_No));
                    sectionsMList.add(sectionsM);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            mAllSection.setValue(sectionsMList);
        }
    }

    public LiveData<List<SectionsM>> getSection() {
        return mAllSection;
    }

}