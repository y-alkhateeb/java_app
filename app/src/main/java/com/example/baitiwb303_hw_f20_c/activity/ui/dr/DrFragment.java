package com.example.baitiwb303_hw_f20_c.activity.ui.dr;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.baitiwb303_hw_f20_c.R;
import com.example.baitiwb303_hw_f20_c.activity.ui.course.CourseViewModel;

public class DrFragment extends Fragment {

    private CourseViewModel DrViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DrViewModel =
                new ViewModelProvider(this).get(CourseViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dr, container, false);
        final TextView textView = root.findViewById(R.id.text_dr);
        DrViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

}