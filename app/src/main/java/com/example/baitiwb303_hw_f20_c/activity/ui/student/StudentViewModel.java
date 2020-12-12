package com.example.baitiwb303_hw_f20_c.activity.ui.student;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.baitiwb303_hw_f20_c.Models.Account;
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

    private final MutableLiveData<List<Account>> mAllAccount;
    private  final List<Account> accountList;
    private DatabaseHelper databaseHelper;
    private JSONArray jsonArray;

    public StudentViewModel(@NonNull Application application) {
        super(application);
        mAllAccount = new MutableLiveData<>();
        accountList = new ArrayList<>();
        databaseHelper = new DatabaseHelper(application.getApplicationContext());
        jsonArray = databaseHelper.get_all_data("account");
        if (jsonArray.length() > 0) {
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    Account account = new Account();
                    account.setAccount_id(jsonArray.getJSONObject(i).getString(Column_Account_ID));
                    account.setFirst_name(jsonArray.getJSONObject(i).getString(Column_Account_First_Name));
                    account.setLast_name(jsonArray.getJSONObject(i).getString(Column_Account_Last_Name));
                    account.setUser_name(jsonArray.getJSONObject(i).getString(Column_Account_User_Name));
                    account.setPassword(jsonArray.getJSONObject(i).getString(Column_Account_Password));
                    account.setReg_Year(jsonArray.getJSONObject(i).getString(Column_Account_Reg_Yeer));
                    account.setGender(jsonArray.getJSONObject(i).getString(Column_Account_Gender));
                    account.setAddress(jsonArray.getJSONObject(i).getString(Column_Account_Address));
                    account.setMobile_No(jsonArray.getJSONObject(i).getString(Column_Account_Mobile));
                    account.setPrivilege(jsonArray.getJSONObject(i).getString(Column_Account_Privilege));
                    accountList.add(account);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            mAllAccount.setValue(accountList);
        }
    }

    public LiveData<List<Account>> getAccount() {
        return mAllAccount;
    }

    public boolean createAccount(Account account){
        return databaseHelper.SignUp(account) != null;
    }

}