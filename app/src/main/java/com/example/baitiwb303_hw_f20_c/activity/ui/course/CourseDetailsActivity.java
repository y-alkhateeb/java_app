package com.example.baitiwb303_hw_f20_c.activity.ui.course;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.baitiwb303_hw_f20_c.Models.AccountM;
import com.example.baitiwb303_hw_f20_c.Models.CourseM;
import com.example.baitiwb303_hw_f20_c.R;
import com.example.baitiwb303_hw_f20_c.activity.ui.student.StudentEditActivity;

public class CourseDetailsActivity extends AppCompatActivity {
    TextView details_course_time, details_course_title;
    Button details_course_delete, details_course_edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);
        Intent intent = getIntent();
        CourseM courseM = (CourseM) intent.getSerializableExtra("course_details");
        details_course_title = findViewById(R.id.details_course_title);
        details_course_time = findViewById(R.id.details_course_hours);
        details_course_delete = findViewById(R.id.details_course_delete);
        details_course_edit = findViewById(R.id.details_course_edit);

        details_course_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        details_course_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editIntent = new Intent(view.getContext(), StudentEditActivity.class);
                editIntent.putExtra("course_details", courseM);
                startActivity(editIntent);
            }
        });


        details_course_title.setText(courseM.getCourse_tittle());
        details_course_time.setText(courseM.getCourse_hours());
    }
}