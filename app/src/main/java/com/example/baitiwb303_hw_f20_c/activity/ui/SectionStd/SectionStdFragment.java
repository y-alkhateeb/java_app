package com.example.baitiwb303_hw_f20_c.activity.ui.SectionStd;

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

import com.example.baitiwb303_hw_f20_c.Models.SectionsM;
import com.example.baitiwb303_hw_f20_c.R;
import com.example.baitiwb303_hw_f20_c.activity.ui.section.SectionAdapter;
import com.example.baitiwb303_hw_f20_c.activity.ui.section.SectionViewModel;

import java.util.List;

public class SectionStdFragment extends Fragment {

    private SectionStdViewModel sectionStdViewModel;
    private SectionAdapter sectionAdapter;
    private RecyclerView sectionRecycleView;
    public static SectionStdFragment newInstance() {
        return new SectionStdFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.section_std_fragment, container, false);
        sectionRecycleView = root.findViewById(R.id.section_std_recycleView);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        sectionStdViewModel = new ViewModelProvider(this).get(SectionStdViewModel.class);

        sectionRecycleView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        sectionRecycleView.setLayoutManager(mLayoutManager);
        sectionRecycleView.setItemAnimator(new DefaultItemAnimator());
        sectionStdViewModel.getSection().observe(getViewLifecycleOwner(), new Observer<List<SectionsM>>() {
            @Override
            public void onChanged(@Nullable List<SectionsM> s) {
                sectionAdapter = new SectionAdapter(getContext(),s);
                sectionRecycleView.setAdapter(sectionAdapter);
            }
        });
    }

}