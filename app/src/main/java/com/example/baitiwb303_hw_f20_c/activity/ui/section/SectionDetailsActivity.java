package com.example.baitiwb303_hw_f20_c.activity.ui.section;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.baitiwb303_hw_f20_c.Models.AccountM;
import com.example.baitiwb303_hw_f20_c.Models.SectionsM;
import com.example.baitiwb303_hw_f20_c.R;
import com.example.baitiwb303_hw_f20_c.activity.ui.course.CourseViewModel;
import com.example.baitiwb303_hw_f20_c.activity.ui.dr.DrViewModel;
import com.example.baitiwb303_hw_f20_c.activity.ui.student.StudentEditActivity;

import java.util.Objects;

public class SectionDetailsActivity extends AppCompatActivity {
    TextView details_section_no, details_section_room,  details_section_time,details_section_course, details_section_instructor;
    Button details_section_delete, details_section_edit;
    private SectionViewModel sectionViewModel;
    private CourseViewModel courseViewModel;
    private DrViewModel drViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_details);

        Intent intent = getIntent();
        SectionsM sectionsM = (SectionsM) intent.getSerializableExtra("section_details");
        sectionViewModel =
                new ViewModelProvider(this).get(SectionViewModel.class);
        courseViewModel =
                new ViewModelProvider(this).get(CourseViewModel.class);
        drViewModel =
                new ViewModelProvider(this).get(DrViewModel.class);

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
                sectionViewModel.delete(sectionsM.getSection_id());
                finish();
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
        for(int i = 0; i< Objects.requireNonNull(courseViewModel.getCourse().getValue()).size(); i++){
            if(courseViewModel.getCourse().getValue().get(i).getCourse_id().equals(sectionsM.getCourse_id())){
                details_section_course.setText(courseViewModel.getCourse().getValue().get(i).getCourse_tittle());
                break;
            }
        }
        for(int i = 0; i< Objects.requireNonNull(drViewModel.getInstructor().getValue()).size(); i++){
            if(drViewModel.getInstructor().getValue().get(i).getInstructor_id().equals(sectionsM.getInstructor_id())){
                details_section_instructor.setText(drViewModel.getInstructor().getValue().get(i).getInstructor_first_name());
                break;
            }
        }
    }
}