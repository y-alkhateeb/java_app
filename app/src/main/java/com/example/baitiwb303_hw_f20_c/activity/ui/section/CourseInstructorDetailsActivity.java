package com.example.baitiwb303_hw_f20_c.activity.ui.section;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baitiwb303_hw_f20_c.Models.CourseM;
import com.example.baitiwb303_hw_f20_c.Models.EnrollmentM;
import com.example.baitiwb303_hw_f20_c.Models.InstructorM;
import com.example.baitiwb303_hw_f20_c.Models.SectionsM;
import com.example.baitiwb303_hw_f20_c.R;
import com.example.baitiwb303_hw_f20_c.Tools.SettingsPref;
import com.example.baitiwb303_hw_f20_c.activity.ui.course.CourseViewModel;
import com.example.baitiwb303_hw_f20_c.activity.ui.dr.DrViewModel;
import com.example.baitiwb303_hw_f20_c.activity.ui.enrollment.EnrollmentViewModel;

import java.util.List;
import java.util.Objects;

public class CourseInstructorDetailsActivity extends AppCompatActivity {
    private TextView details_co_in_section_name, details_co_in_section_room, details_co_in_course_title, details_co_in_course_hours, details_co_in_instructor_firstname, details_co_in_instructor_lastname;
    private Button enrollment_this_course;
    private DrViewModel DrViewModel;
    private CourseViewModel courseViewModel;
    private EnrollmentViewModel enrollmentViewModel;
    private String AccountId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_instructor_details);

        AccountId = SettingsPref.getAccount(this).getAccount_id();
        DrViewModel =
                new ViewModelProvider(this).get(DrViewModel.class);
        courseViewModel =
                new ViewModelProvider(this).get(CourseViewModel.class);
        enrollmentViewModel =
                new ViewModelProvider(this).get(EnrollmentViewModel.class);

        Intent intent = getIntent();
        SectionsM sectionsM = (SectionsM) intent.getSerializableExtra("section_course_instructor_details");


        enrollment_this_course = findViewById(R.id.enrollment_this_course);
        details_co_in_section_name = findViewById(R.id.details_co_in_section_name);
        details_co_in_section_room = findViewById(R.id.details_co_in_section_room);
        details_co_in_course_title = findViewById(R.id.details_co_in_course_title);
        details_co_in_course_hours = findViewById(R.id.details_co_in_course_hours);
        details_co_in_instructor_firstname = findViewById(R.id.details_co_in_instructor_firstname);
        details_co_in_instructor_lastname = findViewById(R.id.details_co_in_instructor_lastname);

        details_co_in_section_name.setText(sectionsM.getSection_section_no());
        details_co_in_section_room.setText(sectionsM.getSection_room_no());
        details_co_in_course_title.setText(sectionsM.getCourse_name());

        DrViewModel.getInstructor().observe(this, new Observer<List<InstructorM>>() {
            @Override
            public void onChanged(@Nullable List<InstructorM> s) {
                for(int i = 0; i< Objects.requireNonNull(s).size(); i++){
                  if(s.get(i).getInstructor_id().equals(sectionsM.getInstructor_id())){
                      details_co_in_instructor_firstname.setText(s.get(i).getInstructor_first_name());
                      details_co_in_instructor_lastname.setText(s.get(i).getInstructor_last_name());
                  }
                }
            }
        });

        courseViewModel.getCourse().observe(this, new Observer<List<CourseM>>() {
            @Override
            public void onChanged(@Nullable List<CourseM> s) {
                for(int i = 0; i< Objects.requireNonNull(s).size(); i++){
                  if(s.get(i).getCourse_id().equals(sectionsM.getCourse_id())){
                      details_co_in_course_hours.setText(s.get(i).getCourse_hours());
                  }
                }
            }
        });

        enrollment_this_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EnrollmentM enrollmentM = new EnrollmentM();
                enrollmentM.setEnrollment_grade("0");
                enrollmentM.setCourse_id(sectionsM.getCourse_id());
                enrollmentM.setSection_id(sectionsM.getSection_id());
                enrollmentM.setAccount_id(AccountId);
                if (enrollmentViewModel.createEnrollment(enrollmentM)){
                    Toast.makeText(CourseInstructorDetailsActivity.this, "You Enrollment this course", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else Toast.makeText(CourseInstructorDetailsActivity.this, "Failed to Enrollment this course", Toast.LENGTH_SHORT).show();
            }
        });
    }
}