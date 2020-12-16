package com.example.baitiwb303_hw_f20_c.activity.ui.student;

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
import com.example.baitiwb303_hw_f20_c.R;

import java.util.List;


public class StudentFragment extends Fragment {

    private StudentViewModel studentViewModel;
    private StudentAdapter studentAdapter;
    private RecyclerView studentRecycleView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        studentViewModel =
                new ViewModelProvider(this).get(StudentViewModel.class);
        View root = inflater.inflate(R.layout.fragment_student, container, false);
        studentRecycleView = root.findViewById(R.id.student_recycleView);
        studentRecycleView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        studentRecycleView.setLayoutManager(mLayoutManager);
        studentRecycleView.setItemAnimator(new DefaultItemAnimator());
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        studentViewModel.getAccount().observe(getViewLifecycleOwner(), new Observer<List<AccountM>>() {
            @Override
            public void onChanged(@Nullable List<AccountM> s) {
                studentAdapter = new StudentAdapter(getContext(),s);
                studentRecycleView.setAdapter(studentAdapter);
            }
        });
    }
}