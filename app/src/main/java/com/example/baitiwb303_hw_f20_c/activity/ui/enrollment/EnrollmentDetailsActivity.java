package com.example.baitiwb303_hw_f20_c.activity.ui.enrollment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.baitiwb303_hw_f20_c.Models.AccountM;
import com.example.baitiwb303_hw_f20_c.Models.CourseM;
import com.example.baitiwb303_hw_f20_c.Models.EnrollmentM;
import com.example.baitiwb303_hw_f20_c.Models.SectionsM;
import com.example.baitiwb303_hw_f20_c.R;
import com.example.baitiwb303_hw_f20_c.Tools.SettingsPref;
import com.example.baitiwb303_hw_f20_c.activity.ui.course.CourseViewModel;
import com.example.baitiwb303_hw_f20_c.activity.ui.dr.DrViewModel;
import com.example.baitiwb303_hw_f20_c.activity.ui.section.SectionViewModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EnrollmentDetailsActivity extends AppCompatActivity {

    private EnrollmentViewModel enrollmentViewModel;
    private EnrollmentDetailsAdapter enrollmentDetailsAdapter;
    private RecyclerView enrollmentDetailsRecycleView;
    private CourseViewModel courseViewModel;
    private DrViewModel drViewModel;
    private SectionViewModel sectionViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int Theme = SettingsPref.getTheme(this);
        if(Theme != 0)
        {
            setTheme(Theme);
        }
        setContentView(R.layout.activity_enrollment_details);
        enrollmentViewModel =
                new ViewModelProvider(this).get(EnrollmentViewModel.class);

        drViewModel =
                new ViewModelProvider(this).get(DrViewModel.class);
        courseViewModel =
                new ViewModelProvider(this).get(CourseViewModel.class);
        sectionViewModel =
                new ViewModelProvider(this).get(SectionViewModel.class);
        enrollmentDetailsRecycleView = findViewById(R.id.enrollment_details_recycleView);
        Intent intent = getIntent();
        AccountM accountM = (AccountM) intent.getSerializableExtra("student_details");

        enrollmentDetailsRecycleView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        enrollmentDetailsRecycleView.setLayoutManager(mLayoutManager);
        enrollmentDetailsRecycleView.setItemAnimator(new DefaultItemAnimator());

        enrollmentViewModel.getEnrollmentDetails(accountM.getAccount_id()).observe(this, new Observer<List<EnrollmentM>>() {
            @Override
            public void onChanged(List<EnrollmentM> enrollmentMS) {
                List<CourseM> courseMS = new ArrayList<>();
                List<SectionsM> sectionsMS = new ArrayList<>();

                for(EnrollmentM enrol : enrollmentMS){
                    for(CourseM c : Objects.requireNonNull(courseViewModel.getCourse().getValue())){
                        if(enrol.getCourse_id().equals(c.getCourse_id())){
                            courseMS.add(c);
                            break;
                        }
                    }

                    for(SectionsM sec : Objects.requireNonNull(sectionViewModel.getSection().getValue())){
                        if(enrol.getSection_id().equals(sec.getSection_id())){
                            sectionsMS.add(sec);
                            break;
                        }
                    }
                }

                enrollmentDetailsAdapter = new EnrollmentDetailsAdapter(getApplication(),courseMS,accountM,sectionsMS,enrollmentMS);
                enrollmentDetailsRecycleView.setAdapter(enrollmentDetailsAdapter);
            }
        });
    }
}