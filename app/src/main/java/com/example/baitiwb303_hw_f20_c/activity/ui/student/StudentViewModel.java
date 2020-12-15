package com.example.baitiwb303_hw_f20_c.activity.ui.student;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.baitiwb303_hw_f20_c.Models.AccountM;
import com.example.baitiwb303_hw_f20_c.database.DatabaseHelper;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import static android.app.PendingIntent.getActivity;
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

public class StudentViewModel extends AndroidViewModel {

    private final MutableLiveData<List<AccountM>> mAllAccount;
    private  final List<AccountM> accountMList;
    private DatabaseHelper databaseHelper;
    private JSONArray jsonArray;

    public StudentViewModel(@NonNull Application application) {
        super(application);
        mAllAccount = new MutableLiveData<>();
        accountMList = new ArrayList<>();
        databaseHelper = new DatabaseHelper(application.getApplicationContext());
        jsonArray = databaseHelper.get_all_data("account");
        if (jsonArray.length() > 0) {
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    AccountM accountM = new AccountM();
                    accountM.setAccount_id(jsonArray.getJSONObject(i).getString(Column_Account_ID));
                    accountM.setFirst_name(jsonArray.getJSONObject(i).getString(Column_Account_First_Name));
                    accountM.setLast_name(jsonArray.getJSONObject(i).getString(Column_Account_Last_Name));
                    accountM.setUser_name(jsonArray.getJSONObject(i).getString(Column_Account_User_Name));
                    accountM.setPassword(jsonArray.getJSONObject(i).getString(Column_Account_Password));
                    accountM.setReg_Year(jsonArray.getJSONObject(i).getString(Column_Account_Reg_Yeer));
                    accountM.setGender(jsonArray.getJSONObject(i).getString(Column_Account_Gender));
                    accountM.setAddress(jsonArray.getJSONObject(i).getString(Column_Account_Address));
                    accountM.setMobile_No(jsonArray.getJSONObject(i).getString(Column_Account_Mobile));
                    accountM.setPrivilege(jsonArray.getJSONObject(i).getString(Column_Account_Privilege));
                    accountMList.add(accountM);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            mAllAccount.setValue(accountMList);
        }
    }

    public LiveData<List<AccountM>> getAccount() {
        return mAllAccount;
    }

    public boolean createAccount(AccountM accountM){
        return databaseHelper.SignUp(accountM) != null;
    }

}