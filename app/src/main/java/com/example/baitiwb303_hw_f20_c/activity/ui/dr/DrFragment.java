package com.example.baitiwb303_hw_f20_c.activity.ui.dr;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.baitiwb303_hw_f20_c.Models.InstructorM;
import com.example.baitiwb303_hw_f20_c.R;
import com.example.baitiwb303_hw_f20_c.activity.ui.course.CourseViewModel;
import com.example.baitiwb303_hw_f20_c.activity.ui.student.StudentAdapter;
import com.example.baitiwb303_hw_f20_c.activity.ui.student.StudentViewModel;

import java.util.List;

public class DrFragment extends Fragment {

    private DrViewModel DrViewModel;
    private InstructorAdapter instructorAdapter;
    private RecyclerView instructorRecycleView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DrViewModel =
                new ViewModelProvider(this).get(DrViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dr, container, false);
        instructorRecycleView = root.findViewById(R.id.instructor_recycleView);
        instructorRecycleView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        instructorRecycleView.setLayoutManager(mLayoutManager);
        instructorRecycleView.setItemAnimator(new DefaultItemAnimator());
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        DrViewModel.getInstructor().observe(getViewLifecycleOwner(), new Observer<List<InstructorM>>() {
            @Override
            public void onChanged(@Nullable List<InstructorM> s) {
                instructorAdapter = new InstructorAdapter(getContext(),s);
                instructorRecycleView.setAdapter(instructorAdapter);
            }
        });
    }
}