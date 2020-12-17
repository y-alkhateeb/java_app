package com.example.baitiwb303_hw_f20_c.activity.ui.section;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.baitiwb303_hw_f20_c.Models.CourseM;
import com.example.baitiwb303_hw_f20_c.Models.InstructorM;
import com.example.baitiwb303_hw_f20_c.Models.SectionsM;
import com.example.baitiwb303_hw_f20_c.R;
import com.example.baitiwb303_hw_f20_c.Tools.SettingsPref;
import com.example.baitiwb303_hw_f20_c.activity.ui.course.CourseViewModel;
import com.example.baitiwb303_hw_f20_c.activity.ui.dr.DrViewModel;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddInstructorAndCoursesToSectionActivity extends AppCompatActivity {
    private Button addSection;
    private Spinner courseSpinner, instructorSpinner;
    private SectionViewModel sectionViewModel;
    private CourseViewModel courseViewModel;
    private DrViewModel drViewModel;
    private ArrayList<String> courseArray;
    private ArrayList<String> drArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int Theme = SettingsPref.getTheme(this);
        if(Theme != 0)
        {
            setTheme(Theme);
        }
        setContentView(R.layout.activity_add_instructor_and_courses_to_section);

        courseArray = new ArrayList<String>();
        drArray = new ArrayList<String>();
        drViewModel =
                new ViewModelProvider(this).get(DrViewModel.class);
        courseViewModel =
                new ViewModelProvider(this).get(CourseViewModel.class);
        sectionViewModel =
                new ViewModelProvider(this).get(SectionViewModel.class);
        courseSpinner = findViewById(R.id.create_section_course_spinner);
        instructorSpinner = findViewById(R.id.create_section_instructor_spinner);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        SectionsM sectionsM = (SectionsM) intent.getSerializableExtra("section_add_co_in");
        courseViewModel.getCourse().observe(this, new Observer<List<CourseM>>() {
            @Override
            public void onChanged(@Nullable List<CourseM> s) {
                for(int i = 0; i< s.size(); i++){
                    courseArray.add(s.get(i).getCourse_tittle());
                }
            }
        });

        drViewModel.getInstructor().observe(this, new Observer<List<InstructorM>>() {
            @Override
            public void onChanged(@Nullable List<InstructorM> s) {
                for(int i = 0; i< s.size(); i++){
                    drArray.add(s.get(i).getInstructor_first_name());
                }
            }
        });

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> courseAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,courseArray);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> drAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,drArray);
        // Specify the layout to use when the list of choices appears
        courseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        drAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        courseSpinner.setAdapter(courseAdapter);
        instructorSpinner.setAdapter(drAdapter);
        addSection =  findViewById(R.id.create_section_course_instructor);
        addSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SettingsPref.getEnableSound(getApplicationContext())){
                    final MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.tone);
                    mp.start();
                }
                sectionsM.setSection_id(sectionsM.getSection_id());
                for(int i = 0; i< Objects.requireNonNull(courseViewModel.getCourse().getValue()).size(); i++){
                    if(courseViewModel.getCourse().getValue().get(i).getCourse_tittle() == courseSpinner.getSelectedItem()){
                        sectionsM.setCourse_id(courseViewModel.getCourse().getValue().get(i).getCourse_id());
                        break;
                    }
                }
                for(int i = 0; i< Objects.requireNonNull(drViewModel.getInstructor().getValue()).size(); i++){
                    if(drViewModel.getInstructor().getValue().get(i).getInstructor_first_name() == instructorSpinner.getSelectedItem()){
                        sectionsM.setInstructor_id(drViewModel.getInstructor().getValue().get(i).getInstructor_id());
                        break;
                    }
                }

                if (sectionViewModel.addCourseAndInstructorToSection(sectionsM)){
                    Snackbar.make(v,"You Added a new student", BaseTransientBottomBar.LENGTH_SHORT);
                    finish();
                }
                else Snackbar.make(v,"Failed to add a new student please try again", BaseTransientBottomBar.LENGTH_SHORT);
            }
        });
    }

}