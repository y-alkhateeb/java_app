package com.example.baitiwb303_hw_f20_c.activity.ui.course;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitiwb303_hw_f20_c.Models.AccountM;
import com.example.baitiwb303_hw_f20_c.Models.CourseM;
import com.example.baitiwb303_hw_f20_c.R;
import com.example.baitiwb303_hw_f20_c.activity.ui.student.StudentAdapter;
import com.example.baitiwb303_hw_f20_c.activity.ui.student.StudentViewModel;

import java.util.List;


public class CourseFragment extends Fragment {

    private CourseViewModel courseViewModel;
    private CourseAdapter courseAdapter;
    private RecyclerView courseRecycleView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        courseViewModel =
                new ViewModelProvider(this).get(CourseViewModel.class);
        View root = inflater.inflate(R.layout.fragment_course, container, false);

        courseRecycleView = root.findViewById(R.id.course_recycleView);
        courseRecycleView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        courseRecycleView.setLayoutManager(mLayoutManager);
        courseRecycleView.setItemAnimator(new DefaultItemAnimator());

        courseViewModel.getCourse().observe(getViewLifecycleOwner(), new Observer<List<CourseM>>() {
            @Override
            public void onChanged(@Nullable List<CourseM> s) {
                courseAdapter = new CourseAdapter(getContext(),s);
                courseRecycleView.setAdapter(courseAdapter);
            }
        });
        return root;
    }
}