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

import com.example.baitiwb303_hw_f20_c.R;


public class StudentFragment extends Fragment {

    private StudentViewModel studentViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        studentViewModel =
                new ViewModelProvider(this).get(StudentViewModel.class);
        View root = inflater.inflate(R.layout.fragment_student, container, false);
        final TextView textView = root.findViewById(R.id.text_student);
        studentViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}