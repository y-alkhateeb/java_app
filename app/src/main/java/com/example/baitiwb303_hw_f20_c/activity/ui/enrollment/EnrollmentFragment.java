package com.example.baitiwb303_hw_f20_c.activity.ui.enrollment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitiwb303_hw_f20_c.Models.AccountM;
import com.example.baitiwb303_hw_f20_c.Models.EnrollmentM;
import com.example.baitiwb303_hw_f20_c.R;
import com.example.baitiwb303_hw_f20_c.Tools.SettingsPref;
import com.example.baitiwb303_hw_f20_c.activity.ui.course.CourseViewModel;
import com.example.baitiwb303_hw_f20_c.activity.ui.dr.DrViewModel;
import com.example.baitiwb303_hw_f20_c.activity.ui.section.CourseAndInstructorViewModel;
import com.example.baitiwb303_hw_f20_c.activity.ui.student.StudentViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class EnrollmentFragment extends Fragment {

    private EnrollmentViewModel enrollmentViewModel;
    private CourseAndInstructorViewModel courseAndInstructorViewModel;
    private StudentEndrollmentAdapter enrollmentAdapter;
    private RecyclerView enrollmentRecycleView;
    private StudentViewModel studentViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        int Theme = SettingsPref.getTheme(getContext());
        if(Theme != 0)
        {
            getActivity().setTheme(Theme);
        }
        View root = inflater.inflate(R.layout.fragment_enrollment, container, false);
        enrollmentRecycleView = root.findViewById(R.id.enrollment_recycleView);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        studentViewModel =
                new ViewModelProvider(this).get(StudentViewModel.class);
        enrollmentViewModel =
                new ViewModelProvider(this).get(EnrollmentViewModel.class);


        enrollmentRecycleView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        enrollmentRecycleView.setLayoutManager(mLayoutManager);
        enrollmentRecycleView.setItemAnimator(new DefaultItemAnimator());
        enrollmentViewModel.getEnrollment().observe(getViewLifecycleOwner(), new Observer<List<EnrollmentM>>() {
            @Override
            public void onChanged(List<EnrollmentM> enrollmentMS) {

            }
        });
        enrollmentViewModel.getEnrollment().observe(getViewLifecycleOwner(), new Observer<List<EnrollmentM>>() {
            @Override
            public void onChanged(@Nullable List<EnrollmentM> s) {
                List<AccountM> accountMList = new ArrayList<>();
                for(EnrollmentM enrollmentM : s){
                    for(AccountM a : Objects.requireNonNull(studentViewModel.getAccount().getValue())){
                        if(a.getAccount_id().contains(enrollmentM.getAccount_id())){
                            accountMList.add(a);
                        }
                    }
                }
                enrollmentAdapter = new StudentEndrollmentAdapter(getContext(),accountMList);
                enrollmentRecycleView.setAdapter(enrollmentAdapter);
            }
        });
    }
}