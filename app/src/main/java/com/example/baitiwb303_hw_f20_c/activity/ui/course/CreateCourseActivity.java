package com.example.baitiwb303_hw_f20_c.activity.ui.course;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.baitiwb303_hw_f20_c.Models.CourseM;
import com.example.baitiwb303_hw_f20_c.R;
import com.example.baitiwb303_hw_f20_c.Tools.SettingsPref;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class CreateCourseActivity extends AppCompatActivity {
    private EditText course_title, course_hours;
    private Button addCourse;
    private CourseViewModel courseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_course);

        setContentView(R.layout.activity_create_course);
        courseViewModel =
                new ViewModelProvider(this).get(CourseViewModel.class);

        course_title = findViewById(R.id.create_course_title);
        course_hours =  findViewById(R.id.create_course_hours);
        addCourse =  findViewById(R.id.create_course);

        addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SettingsPref.getEnableSound(getApplicationContext())){
                    final MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.tone);
                    mp.start();
                }
                CourseM courseM = new CourseM();
                courseM.setCourse_tittle(course_title.getText().toString());
                courseM.setCourse_hours(course_hours.getText().toString());

                if (courseViewModel.createCourse(courseM)){
                    Snackbar.make(v,"You Added a new course", BaseTransientBottomBar.LENGTH_SHORT);
                    finish();
                }
                else Snackbar.make(v,"Failed to add a new student please try again", BaseTransientBottomBar.LENGTH_SHORT);
            }
        });


    }
}