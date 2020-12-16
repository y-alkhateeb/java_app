package com.example.baitiwb303_hw_f20_c.activity.ui.dr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.baitiwb303_hw_f20_c.Models.InstructorM;
import com.example.baitiwb303_hw_f20_c.R;

public class InstructorDetailsActivity extends AppCompatActivity {
    TextView details_instructor_firstname, details_instructor_lastname, details_instructor_mobile, details_instructor_gender, details_instructor_address;
    Button details_instructor_delete, details_instructor_edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_details);

        Intent intent = getIntent();
        InstructorM instructorM = (InstructorM) intent.getSerializableExtra("instructor_details");
        details_instructor_firstname = findViewById(R.id.details_instructor_firstname);
        details_instructor_lastname = findViewById(R.id.details_instructor_lastname);
        details_instructor_mobile = findViewById(R.id.details_instructor_mobile);
        details_instructor_gender = findViewById(R.id.details_instructor_gender);
        details_instructor_address = findViewById(R.id.details_instructor_address);
        details_instructor_delete = findViewById(R.id.details_instructor_delete);
        details_instructor_edit = findViewById(R.id.details_instructor_edit);

        details_instructor_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        details_instructor_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editIntent = new Intent(view.getContext(), InstructorEditActivity.class);
                editIntent.putExtra("instructor_details", instructorM);
                startActivity(editIntent);
            }
        });

        details_instructor_firstname.setText(instructorM.getInstructor_first_name());
        details_instructor_lastname.setText(instructorM.getInstructor_last_name());
        details_instructor_mobile.setText(instructorM.getInstructor_mobile());
        details_instructor_gender.setText(instructorM.getInstructor_gender());
        details_instructor_address.setText(instructorM.getInstructor_address());
    }
}