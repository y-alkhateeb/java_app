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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import com.example.baitiwb303_hw_f20_c.activity.MainActivity;

public class SectionFragment extends Fragment {

    private SectionViewModel sectionViewModel;
    private SectionAdapter sectionAdapter;
    private RecyclerView sectionRecycleView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View root = inflater.inflate(R.layout.fragment_section, container, false);
        sectionRecycleView = root.findViewById(R.id.section_recycleView);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        sectionViewModel =
                new ViewModelProvider(this).get(SectionViewModel.class);
        sectionRecycleView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        sectionRecycleView.setLayoutManager(mLayoutManager);
        sectionRecycleView.setItemAnimator(new DefaultItemAnimator());
        sectionViewModel.getSection().observe(getViewLifecycleOwner(), new Observer<List<SectionsM>>() {
            @Override
            public void onChanged(@Nullable List<SectionsM> s) {
                sectionAdapter = new SectionAdapter(getContext(),s);
                sectionRecycleView.setAdapter(sectionAdapter);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = new SearchView(((MainActivity) getContext()).getSupportActionBar().getThemedContext());
        // MenuItemCompat.setShowAsAction(item, //MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | //MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
        //  MenuItemCompat.setActionView(item, searchView);
        // These lines are deprecated in API 26 use instead
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItem.SHOW_AS_ACTION_IF_ROOM);
        item.setActionView(searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
//                studentAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                sectionAdapter.getFilter().filter(newText);
                return false;
            }
        });
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}