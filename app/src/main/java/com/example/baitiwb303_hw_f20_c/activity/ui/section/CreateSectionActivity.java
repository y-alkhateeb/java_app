package com.example.baitiwb303_hw_f20_c.activity.ui.section;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.baitiwb303_hw_f20_c.Models.SectionsM;
import com.example.baitiwb303_hw_f20_c.R;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class CreateSectionActivity extends AppCompatActivity {

    private EditText sectionNo, section_room, sectionTime;
    private Button addSection;
    private SectionViewModel sectionViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_section);
        sectionViewModel =
                new ViewModelProvider(this).get(SectionViewModel.class);


    }


    @Override
    protected void onResume() {
        super.onResume();
        sectionNo =  findViewById(R.id.create_section_no);
        section_room =  findViewById(R.id.create_section_room);
        sectionTime =  findViewById(R.id.create_section_time);
        addSection =  findViewById(R.id.create_section);
        addSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SectionsM sectionsM = new SectionsM();
                sectionsM.setSection_room_no(section_room.getText().toString());
                sectionsM.setSection_section_no(sectionNo.getText().toString());
                sectionsM.setSection_time(sectionTime.getText().toString());

                if (sectionViewModel.createSection(sectionsM)){
                    Snackbar.make(v,"You Added a new student", BaseTransientBottomBar.LENGTH_SHORT);
                    finish();
                }
                else Snackbar.make(v,"Failed to add a new student please try again", BaseTransientBottomBar.LENGTH_SHORT);
            }
        });
    }
}