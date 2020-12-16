package com.example.baitiwb303_hw_f20_c.activity.ui.dr;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.baitiwb303_hw_f20_c.Models.AccountM;
import com.example.baitiwb303_hw_f20_c.Models.InstructorM;
import com.example.baitiwb303_hw_f20_c.database.DatabaseHelper;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import static com.example.baitiwb303_hw_f20_c.database.DatabaseHelper.Column_Account_Address;
import static com.example.baitiwb303_hw_f20_c.database.DatabaseHelper.Column_Account_First_Name;
import static com.example.baitiwb303_hw_f20_c.database.DatabaseHelper.Column_Account_Gender;
import static com.example.baitiwb303_hw_f20_c.database.DatabaseHelper.Column_Account_ID;
import static com.example.baitiwb303_hw_f20_c.database.DatabaseHelper.Column_Account_Last_Name;
import static com.example.baitiwb303_hw_f20_c.database.DatabaseHelper.Column_Account_Mobile;
import static com.example.baitiwb303_hw_f20_c.database.DatabaseHelper.Column_Account_Password;
import static com.example.baitiwb303_hw_f20_c.database.DatabaseHelper.Column_Account_Privilege;
import static com.example.baitiwb303_hw_f20_c.database.DatabaseHelper.Column_Account_Reg_Yeer;
import static com.example.baitiwb303_hw_f20_c.database.DatabaseHelper.Column_Account_User_Name;
import static com.example.baitiwb303_hw_f20_c.database.DatabaseHelper.Column_Instructor_Address;
import static com.example.baitiwb303_hw_f20_c.database.DatabaseHelper.Column_Instructor_First_Name;
import static com.example.baitiwb303_hw_f20_c.database.DatabaseHelper.Column_Instructor_Gender;
import static com.example.baitiwb303_hw_f20_c.database.DatabaseHelper.Column_Instructor_ID;
import static com.example.baitiwb303_hw_f20_c.database.DatabaseHelper.Column_Instructor_Last_Name;
import static com.example.baitiwb303_hw_f20_c.database.DatabaseHelper.Column_Instructor_Mobile;

public class DrViewModel extends AndroidViewModel {

    private final MutableLiveData<List<InstructorM>> mAllInstructorM;
    private  final List<InstructorM> instructorMMList;
    private DatabaseHelper databaseHelper;
    private JSONArray jsonArray;

    public DrViewModel(@NonNull Application application) {
        super(application);
        mAllInstructorM = new MutableLiveData<>();
        instructorMMList = new ArrayList<>();
        databaseHelper = new DatabaseHelper(application.getApplicationContext());
        jsonArray = databaseHelper.get_all_data("instructor");
        if (jsonArray.length() > 0) {
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    InstructorM instructorM = new InstructorM();
                    instructorM.setInstructor_id(jsonArray.getJSONObject(i).getString(Column_Instructor_ID));
                    instructorM.setInstructor_first_name(jsonArray.getJSONObject(i).getString(Column_Instructor_First_Name));
                    instructorM.setInstructor_last_name(jsonArray.getJSONObject(i).getString(Column_Instructor_Last_Name));
                    instructorM.setInstructor_gender(jsonArray.getJSONObject(i).getString(Column_Instructor_Gender));
                    instructorM.setInstructor_address(jsonArray.getJSONObject(i).getString(Column_Instructor_Address));
                    instructorM.setInstructor_mobile(jsonArray.getJSONObject(i).getString(Column_Instructor_Mobile));
                    instructorMMList.add(instructorM);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            mAllInstructorM.setValue(instructorMMList);
        }
    }

    public LiveData<List<InstructorM>> getInstructor() {
        return mAllInstructorM;
    }

    public boolean createInstructor(InstructorM instructorM){
        return databaseHelper.CreateInstructor(instructorM) != null;
    }

}