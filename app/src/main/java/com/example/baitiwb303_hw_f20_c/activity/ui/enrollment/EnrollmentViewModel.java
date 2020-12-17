package com.example.baitiwb303_hw_f20_c.activity.ui.enrollment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.baitiwb303_hw_f20_c.Models.EnrollmentM;
import com.example.baitiwb303_hw_f20_c.Models.SectionsM;
import com.example.baitiwb303_hw_f20_c.database.DatabaseHelper;
import com.example.baitiwb303_hw_f20_c.database.Enrollment;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import static com.example.baitiwb303_hw_f20_c.database.DatabaseHelper.Column_Account_ID;
import static com.example.baitiwb303_hw_f20_c.database.DatabaseHelper.Column_Course_ID;
import static com.example.baitiwb303_hw_f20_c.database.DatabaseHelper.Column_Enrollment_Grade;
import static com.example.baitiwb303_hw_f20_c.database.DatabaseHelper.Column_Section_ID;
import static com.example.baitiwb303_hw_f20_c.database.DatabaseHelper.Column_Section_No;
import static com.example.baitiwb303_hw_f20_c.database.DatabaseHelper.Column_Section_Room_No;
import static com.example.baitiwb303_hw_f20_c.database.DatabaseHelper.Column_Section_Time;

public class EnrollmentViewModel extends AndroidViewModel {


    private final MutableLiveData<List<EnrollmentM>> mAllEnrollment;
    private final List<EnrollmentM> enrollmentMS;
    private final DatabaseHelper databaseHelper;
    private JSONArray jsonArray;

    public EnrollmentViewModel(@NonNull Application application) {
        super(application);
        mAllEnrollment = new MutableLiveData<>();
        enrollmentMS = new ArrayList<>();
        databaseHelper = new DatabaseHelper(application.getApplicationContext());
        jsonArray = databaseHelper.get_all_data_groupby("enrollment",Column_Account_ID);
        if (jsonArray.length() > 0) {
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    EnrollmentM enrollmentM = new EnrollmentM();
                    enrollmentM.setSection_id(jsonArray.getJSONObject(i).getString(Column_Section_ID));
                    enrollmentM.setAccount_id(jsonArray.getJSONObject(i).getString(Column_Account_ID));
                    enrollmentM.setCourse_id(jsonArray.getJSONObject(i).getString(Column_Course_ID));
                    enrollmentM.setEnrollment_grade(jsonArray.getJSONObject(i).getString(Column_Enrollment_Grade));
                    enrollmentMS.add(enrollmentM);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            mAllEnrollment.setValue(enrollmentMS);
        }
    }

    public LiveData<List<EnrollmentM>> getEnrollment() {
        return mAllEnrollment;
    }


    public LiveData<List<EnrollmentM>> getEnrollmentDetails(String AccountId) {
        jsonArray = databaseHelper.get_all_data_byId("enrollment",AccountId);
        if (jsonArray.length() > 0) {
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    EnrollmentM enrollmentM = new EnrollmentM();
                    enrollmentM.setSection_id(jsonArray.getJSONObject(i).getString(Column_Section_ID));
                    enrollmentM.setAccount_id(jsonArray.getJSONObject(i).getString(Column_Account_ID));
                    enrollmentM.setCourse_id(jsonArray.getJSONObject(i).getString(Column_Course_ID));
                    enrollmentM.setEnrollment_grade(jsonArray.getJSONObject(i).getString(Column_Enrollment_Grade));
                    enrollmentMS.add(enrollmentM);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            mAllEnrollment.setValue(enrollmentMS);
        }
        return mAllEnrollment;
    }


    public boolean createEnrollment(EnrollmentM enrollmentM){
        return  databaseHelper.CreateEnrollment(enrollmentM) != null;
    }

    public void delete(String id){
        databaseHelper.DeleteRawInTable("section",Column_Section_ID,id);
    }
}
