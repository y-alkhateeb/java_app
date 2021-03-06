package com.example.baitiwb303_hw_f20_c.activity.ui.course;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.baitiwb303_hw_f20_c.Models.AccountM;
import com.example.baitiwb303_hw_f20_c.Models.CourseM;
import com.example.baitiwb303_hw_f20_c.R;
import com.example.baitiwb303_hw_f20_c.Tools.SettingsPref;
import com.example.baitiwb303_hw_f20_c.database.DatabaseHelper;

public class CourseEditActivity extends AppCompatActivity {
    private EditText course_title, course_hours;
    private Button addCourse;
    private CourseViewModel courseViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_edit);
        courseViewModel =
                new ViewModelProvider(this).get(CourseViewModel.class);
        Intent intent = getIntent();
        CourseM courseM = (CourseM) intent.getSerializableExtra("course_details");
        course_title = findViewById(R.id.edit_course_title);
        course_hours = findViewById(R.id.edit_course_hours);
        addCourse = findViewById(R.id.edit_save_change_course);

        addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SettingsPref.getEnableSound(getApplicationContext())){
                    final MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.tone);
                    mp.start();
                }
                courseM.setCourse_id(courseM.getCourse_id());
                courseM.setCourse_tittle(course_title.getText().toString());
                courseM.setCourse_hours(course_hours.getText().toString());
                courseViewModel.updateCourse(courseM);
                finish();
            }
        });


        course_title.setText(courseM.getCourse_tittle());
        course_hours.setText(courseM.getCourse_hours());

    }
}