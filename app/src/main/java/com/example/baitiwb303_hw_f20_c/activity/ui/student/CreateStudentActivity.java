package com.example.baitiwb303_hw_f20_c.activity.ui.student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.baitiwb303_hw_f20_c.Models.Account;
import com.example.baitiwb303_hw_f20_c.R;

public class CreateStudentActivity extends AppCompatActivity {
    private EditText username, password, firstName, lastName, address, phoneNumber;
    private Button addStudent;
    private Spinner spinner;
    private StudentViewModel studentViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_student);
        ((AppCompatActivity) this).getSupportActionBar().setTitle("Add New Student");
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
                Account account = new Account();
                account.setUser_name(username.getText().toString());
                account.setPassword(password.getText().toString());
                account.setGender(spinner.getSelectedItem().toString());
                account.setFirst_name(firstName.getText().toString());
                account.setLast_name(lastName.getText().toString());
                account.setAddress(address.getText().toString());
                account.setMobile_No(phoneNumber.getText().toString());
                account.setPrivilege("2");

                studentViewModel.createAccount(account);
            }
        });
    }
}