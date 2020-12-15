package com.example.baitiwb303_hw_f20_c.activity.ui.section;

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

import com.example.baitiwb303_hw_f20_c.Models.SectionsM;
import com.example.baitiwb303_hw_f20_c.R;

import java.util.List;


public class SectionFragment extends Fragment {

    private SectionViewModel sectionViewModel;
    private SectionAdapter sectionAdapter;
    private RecyclerView sectionRecycleView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sectionViewModel =
                new ViewModelProvider(this).get(SectionViewModel.class);
        View root = inflater.inflate(R.layout.fragment_section, container, false);
        
        sectionRecycleView = root.findViewById(R.id.section_recycleView);
        sectionRecycleView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        sectionRecycleView.setLayoutManager(mLayoutManager);
        sectionRecycleView.setItemAnimator(new DefaultItemAnimator());

//        sectionViewModel.getText().observe(getViewLifecycleOwner(), new Observer<List<SectionsM>>() {
//            @Override
//            public void onChanged(@Nullable List<SectionsM> s) {
//                sectionAdapter = new SectionAdapter(getContext(),s);
//                sectionRecycleView.setAdapter(sectionAdapter);
//            }
//        });
        return root;
    }
}