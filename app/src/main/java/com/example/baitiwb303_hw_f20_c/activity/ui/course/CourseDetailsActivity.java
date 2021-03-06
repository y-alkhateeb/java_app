package com.example.baitiwb303_hw_f20_c.activity.ui.course;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.baitiwb303_hw_f20_c.Models.CourseM;
import com.example.baitiwb303_hw_f20_c.R;
import com.example.baitiwb303_hw_f20_c.Tools.SettingsPref;

public class CourseDetailsActivity extends AppCompatActivity {
    TextView details_course_time, details_course_title;
    Button details_course_delete, details_course_edit;
    private CourseViewModel courseViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);
        courseViewModel =
                new ViewModelProvider(this).get(CourseViewModel.class);
        Intent intent = getIntent();
        CourseM courseM = (CourseM) intent.getSerializableExtra("course_details");
        details_course_title = findViewById(R.id.details_course_title);
        details_course_time = findViewById(R.id.details_course_hours);
        details_course_delete = findViewById(R.id.details_course_delete);
        details_course_edit = findViewById(R.id.details_course_edit);

        details_course_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SettingsPref.getEnableSound(getApplicationContext())){
                    final MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.tone);
                    mp.start();
                }
                courseViewModel.delete(courseM.getCourse_id());
                finish();
            }
        });
        details_course_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SettingsPref.getEnableSound(getApplicationContext())){
                    final MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.tone);
                    mp.start();
                }
                Intent editIntent = new Intent(view.getContext(), CourseEditActivity.class);
                editIntent.putExtra("course_details", courseM);
                startActivity(editIntent);
            }
        });


        details_course_title.setText(courseM.getCourse_tittle());
        details_course_time.setText(courseM.getCourse_hours());
    }
}