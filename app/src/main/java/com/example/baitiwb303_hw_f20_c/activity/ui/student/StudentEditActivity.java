package com.example.baitiwb303_hw_f20_c.activity.ui.student;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.baitiwb303_hw_f20_c.Models.AccountM;
import com.example.baitiwb303_hw_f20_c.R;
import com.example.baitiwb303_hw_f20_c.database.DatabaseHelper;

public class StudentEditActivity extends AppCompatActivity {
    private EditText username, password, firstName, lastName, address, phoneNumber;
    private Button addStudent;
    private Spinner spinner;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_edit);
        databaseHelper = new DatabaseHelper(this);
        spinner = findViewById(R.id.edit_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        username = findViewById(R.id.edit_username);
        password = findViewById(R.id.edit_password);
        firstName = findViewById(R.id.edit_first_name);
        lastName = findViewById(R.id.edit_last_name);
        phoneNumber = findViewById(R.id.edit_phone);
        address = findViewById(R.id.edit_address);
        addStudent = findViewById(R.id.edit_save_change_student);
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

            }
        });


        Intent intent = getIntent();
        AccountM accountM = (AccountM) intent.getSerializableExtra("student_details");
        username.setText(accountM.getUser_name());
        firstName.setText(accountM.getFirst_name());
        lastName.setText(accountM.getLast_name());
        phoneNumber.setText(accountM.getMobile_No());
        address.setText(accountM.getAddress());
        if(accountM.getGender().equals("Male"))
            spinner.setSelection(0);
        else spinner.setSelection(1);
    }
}