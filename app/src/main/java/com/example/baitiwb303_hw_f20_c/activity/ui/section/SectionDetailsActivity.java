package com.example.baitiwb303_hw_f20_c.activity.ui.section;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.baitiwb303_hw_f20_c.Models.AccountM;
import com.example.baitiwb303_hw_f20_c.Models.SectionsM;
import com.example.baitiwb303_hw_f20_c.R;
import com.example.baitiwb303_hw_f20_c.activity.ui.student.StudentEditActivity;

public class SectionDetailsActivity extends AppCompatActivity {
    TextView details_section_no, details_section_room,  details_section_time,details_section_course, details_section_instructor;
    Button details_section_delete, details_section_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_details);

        Intent intent = getIntent();
        SectionsM sectionsM = (SectionsM) intent.getSerializableExtra("section_details");
        details_section_no = findViewById(R.id.details_section_name);
        details_section_room = findViewById(R.id.details_section_room);
        details_section_time = findViewById(R.id.details_section_time);
        details_section_course = findViewById(R.id.details_section_course);
        details_section_instructor = findViewById(R.id.details_section_instructor);
        details_section_delete = findViewById(R.id.details_section_delete);
        details_section_edit = findViewById(R.id.details_section_edit);

        details_section_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        details_section_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editIntent = new Intent(view.getContext(), SectionEditActivity.class);
                editIntent.putExtra("section_details", sectionsM);
                startActivity(editIntent);
            }
        });

        details_section_no.setText(sectionsM.getSection_section_no());
        details_section_room.setText(sectionsM.getSection_room_no());
        details_section_time.setText(sectionsM.getSection_time());
        details_section_course.setText(sectionsM.getCourse_id());
        details_section_instructor.setText(sectionsM.getInstructor_id());
    }
}