package com.example.baitiwb303_hw_f20_c.activity;

import android.content.Intent;
import android.util.Patterns;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.baitiwb303_hw_f20_c.Models.Account;
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
    boolean isEmailValid, isPasswordValid;
    TextInputLayout emailError, passError;
    DatabaseHelper databaseHelper;
    JSONArray Response_SignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        databaseHelper = new DatabaseHelper(this);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        emailError = (TextInputLayout) findViewById(R.id.emailError);
        passError = (TextInputLayout) findViewById(R.id.passError);

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
            isEmailValid = false;
        } else {
            isEmailValid = true;
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

        if (isEmailValid && isPasswordValid) {
            Response_SignIn = databaseHelper.SignIn("account", username.getText().toString(), password.getText().toString());
            if (Response_SignIn.length() > 0) {
                JSONObject json_data_sign_in = new JSONObject();
                for (int i = 0; i < Response_SignIn.length(); i++) {
                    try {
                        json_data_sign_in = Response_SignIn.getJSONObject(i);
                        Account account = new Account();
                        account.setAccount_id(json_data_sign_in.getString(Column_Account_ID));
                        account.setFirst_name(json_data_sign_in.getString(Column_Account_First_Name));
                        account.setLast_name(json_data_sign_in.getString(Column_Account_Last_Name));
                        account.setUser_name(json_data_sign_in.getString(Column_Account_User_Name));
                        account.setPassword(json_data_sign_in.getString(Column_Account_Password));
                        account.setReg_Year(json_data_sign_in.getString(Column_Account_Reg_Yeer));
                        account.setGender(json_data_sign_in.getString(Column_Account_Gender));
                        account.setAddress(json_data_sign_in.getString(Column_Account_Address));
                        account.setMobile_No(json_data_sign_in.getString(Column_Account_Mobile));
                        account.setPrivilege(json_data_sign_in.getString(Column_Account_Privilege));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Toast.makeText(getApplicationContext(), "Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                this.finish();
                startActivity(intent);
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Error UserName or Password", Toast.LENGTH_SHORT).show();

            }
        }

    }

}