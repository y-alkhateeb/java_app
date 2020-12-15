package com.example.baitiwb303_hw_f20_c.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.baitiwb303_hw_f20_c.Models.AccountM;
import com.example.baitiwb303_hw_f20_c.R;
import com.example.baitiwb303_hw_f20_c.database.DatabaseHelper;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button login;
    boolean isUserNameValid, isPasswordValid;
    TextInputLayout emailError, passError;
    DatabaseHelper databaseHelper;
    JSONArray Response_SignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        databaseHelper = new DatabaseHelper(this);
        username = findViewById(R.id.username);
        password =  findViewById(R.id.password);
        login =  findViewById(R.id.login);
        emailError = findViewById(R.id.emailError);
        passError =  findViewById(R.id.passError);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetValidation();
            }
        });
    }

    public void SetValidation() {
        // Check for a valid email address.
        if (username.getText().toString().isEmpty()) {
            emailError.setError(getResources().getString(R.string.username_error));
            isUserNameValid = false;
        } else {
            isUserNameValid = true;
            emailError.setErrorEnabled(false);
        }

        // Check for a valid password.
        if (password.getText().toString().isEmpty()) {
            passError.setError(getResources().getString(R.string.password_error));
            isPasswordValid = false;
        } else if (password.getText().length() < 6) {
            passError.setError(getResources().getString(R.string.error_invalid_password));
            isPasswordValid = false;
        } else {
            isPasswordValid = true;
            passError.setErrorEnabled(false);
        }

        if (isUserNameValid && isPasswordValid) {
            Response_SignIn = databaseHelper.SignIn(username.getText().toString(), password.getText().toString());
            if (Response_SignIn.length() > 0) {
                try {
                    Toast.makeText(getApplicationContext(), "Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, MainActivity.class);
                    JSONObject json_data_sign_in;
                    json_data_sign_in = Response_SignIn.getJSONObject(0);
                    AccountM accountM = new AccountM();
                    accountM.setAccount_id(json_data_sign_in.getString(Column_Account_ID));
                    accountM.setFirst_name(json_data_sign_in.getString(Column_Account_First_Name));
                    accountM.setLast_name(json_data_sign_in.getString(Column_Account_Last_Name));
                    accountM.setUser_name(json_data_sign_in.getString(Column_Account_User_Name));
                    accountM.setPassword(json_data_sign_in.getString(Column_Account_Password));
                    accountM.setReg_Year(json_data_sign_in.getString(Column_Account_Reg_Yeer));
                    accountM.setGender(json_data_sign_in.getString(Column_Account_Gender));
                    accountM.setAddress(json_data_sign_in.getString(Column_Account_Address));
                    accountM.setMobile_No(json_data_sign_in.getString(Column_Account_Mobile));
                    accountM.setPrivilege(json_data_sign_in.getString(Column_Account_Privilege));
                    intent.putExtra("LOGIN_INFO", accountM);
                    this.finish();
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Error UserName or Password", Toast.LENGTH_SHORT).show();

            }
        }

    }

}