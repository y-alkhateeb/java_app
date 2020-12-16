package com.example.baitiwb303_hw_f20_c.activity.ui.student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.baitiwb303_hw_f20_c.Models.AccountM;
import com.example.baitiwb303_hw_f20_c.R;

public class StudentDetailsActivity extends AppCompatActivity {
    TextView details_student_username, details_student_firstname, details_student_lastname, details_student_mobile, details_student_gender, details_student_address;
    Button details_student_delete, details_student_edit;
    private StudentViewModel studentViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);
        studentViewModel =
                new ViewModelProvider(this).get(StudentViewModel.class);
        Intent intent = getIntent();
        AccountM accountM = (AccountM) intent.getSerializableExtra("student_details");
        details_student_username = findViewById(R.id.details_student_username);
        details_student_firstname = findViewById(R.id.details_student_firstname);
        details_student_lastname = findViewById(R.id.details_student_lastname);
        details_student_mobile = findViewById(R.id.details_student_mobile);
        details_student_gender = findViewById(R.id.details_student_gender);
        details_student_address = findViewById(R.id.details_student_address);
        details_student_delete = findViewById(R.id.details_student_delete);
        details_student_edit = findViewById(R.id.details_student_edit);

        details_student_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studentViewModel.delete(accountM.getAccount_id());
                finish();
            }
        });
        details_student_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editIntent = new Intent(view.getContext(),StudentEditActivity.class);
                editIntent.putExtra("student_details", accountM);
                startActivity(editIntent);
            }
        });

        details_student_username.setText(accountM.getUser_name());
        details_student_firstname.setText(accountM.getFirst_name());
        details_student_lastname.setText(accountM.getLast_name());
        details_student_mobile.setText(accountM.getMobile_No());
        details_student_gender.setText(accountM.getGender());
        details_student_address.setText(accountM.getAddress());
    }
}