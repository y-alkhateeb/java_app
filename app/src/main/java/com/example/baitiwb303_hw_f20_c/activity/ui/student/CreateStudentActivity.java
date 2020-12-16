package com.example.baitiwb303_hw_f20_c.activity.ui.student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.baitiwb303_hw_f20_c.Models.AccountM;
import com.example.baitiwb303_hw_f20_c.R;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class CreateStudentActivity extends AppCompatActivity {
    private EditText username, password, firstName, lastName, address, phoneNumber;
    private Button addStudent;
    private Spinner spinner;
    private StudentViewModel studentViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_student);
        studentViewModel =
                new ViewModelProvider(this).get(StudentViewModel.class);
        spinner = findViewById(R.id.create_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        username = findViewById(R.id.create_username);
        password =  findViewById(R.id.create_password);
        firstName =  findViewById(R.id.create_first_name);
        lastName =  findViewById(R.id.create_last_name);
        phoneNumber =  findViewById(R.id.create_phone);
        address =  findViewById(R.id.create_address);
        addStudent =  findViewById(R.id.create_account);
        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountM accountM = new AccountM();
                accountM.setUser_name(username.getText().toString());
                accountM.setPassword(password.getText().toString());
                accountM.setGender(spinner.getSelectedItem().toString());
                accountM.setFirst_name(firstName.getText().toString());
                accountM.setLast_name(lastName.getText().toString());
                accountM.setAddress(address.getText().toString());
                accountM.setMobile_No(phoneNumber.getText().toString());
                accountM.setPrivilege("2");

                if (studentViewModel.createAccount(accountM)){
                    Snackbar.make(v,"You Added a new student", BaseTransientBottomBar.LENGTH_SHORT);
                    finish();
                }
                else Snackbar.make(v,"Failed to add a new student please try again", BaseTransientBottomBar.LENGTH_SHORT);
            }
        });
    }
}