package com.example.baitiwb303_hw_f20_c.activity.ui.section;

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


public class SectionFragment extends Fragment {

    private SectionModel sectionModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sectionModel =
                new ViewModelProvider(this).get(SectionModel.class);
        View root = inflater.inflate(R.layout.fragment_section, container, false);
        final TextView textView = root.findViewById(R.id.text_section);
        sectionModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}