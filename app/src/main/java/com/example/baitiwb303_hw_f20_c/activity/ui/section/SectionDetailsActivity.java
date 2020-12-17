package com.example.baitiwb303_hw_f20_c.activity.ui.section;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.baitiwb303_hw_f20_c.Models.SectionsM;
import com.example.baitiwb303_hw_f20_c.R;
import com.example.baitiwb303_hw_f20_c.activity.ui.course.CourseViewModel;
import com.example.baitiwb303_hw_f20_c.activity.ui.dr.DrViewModel;

import java.util.List;
import java.util.Objects;

public class SectionDetailsActivity extends AppCompatActivity {
    TextView details_section_no, details_section_room,  details_section_time;
    Button details_section_delete, details_section_edit, details_section_add;
    private SectionViewModel sectionViewModel;
    private CourseViewModel courseViewModel;
    private DrViewModel drViewModel;

    private CourseAndInstructorAdapter courseAndInstructorAdapter;
    private RecyclerView sectionRecycleView;
    private CourseAndInstructorViewModel courseAndInstructorViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_details);
        courseAndInstructorViewModel =
                new ViewModelProvider(this).get(CourseAndInstructorViewModel.class);
        sectionRecycleView = findViewById(R.id.course_and_instructor_recycleView);
        sectionRecycleView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        sectionRecycleView.setLayoutManager(mLayoutManager);
        sectionRecycleView.setItemAnimator(new DefaultItemAnimator());
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
        details_section_add = findViewById(R.id.details_section_add);
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

        details_section_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addIntent = new Intent(view.getContext(), AddInstructorAndCoursesToSectionActivity.class);
                addIntent.putExtra("section_add_co_in", sectionsM);
                startActivity(addIntent);
            }
        });

        details_section_no.setText(sectionsM.getSection_section_no());
        details_section_room.setText(sectionsM.getSection_room_no());
        details_section_time.setText(sectionsM.getSection_time());

        courseAndInstructorViewModel.getCourseAndInstructor(sectionsM.getSection_id()).observe(this, new Observer<List<SectionsM>>() {
            @Override
            public void onChanged(@Nullable List<SectionsM> s) {
                for(int i = 0; i< Objects.requireNonNull(s).size(); i++){
                    s.get(i).setSection_section_no(sectionsM.getSection_section_no());
                    s.get(i).setSection_room_no(sectionsM.getSection_room_no());

                    for (int j = 0; j < Objects.requireNonNull(courseViewModel.getCourse().getValue()).size(); j++) {
                        if (courseViewModel.getCourse().getValue().get(j).getCourse_id().equals(s.get(i).getCourse_id())) {
                            s.get(i).setCourse_name(courseViewModel.getCourse().getValue().get(j).getCourse_tittle());
                        }
                    }
                    for (int k = 0; k < Objects.requireNonNull(drViewModel.getInstructor().getValue()).size(); k++) {
                        if (drViewModel.getInstructor().getValue().get(k).getInstructor_id().equals(s.get(i).getInstructor_id())) {
                            s.get(i).setInstructor_name(drViewModel.getInstructor().getValue().get(k).getInstructor_first_name());
                        }
                    }


                }

                courseAndInstructorAdapter = new CourseAndInstructorAdapter(getApplicationContext(),s);
                sectionRecycleView.setAdapter(courseAndInstructorAdapter);
            }
        });

    }
}