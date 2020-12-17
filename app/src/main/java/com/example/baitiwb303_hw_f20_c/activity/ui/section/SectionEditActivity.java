package com.example.baitiwb303_hw_f20_c.activity.ui.section;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.baitiwb303_hw_f20_c.Models.SectionsM;
import com.example.baitiwb303_hw_f20_c.R;
import com.example.baitiwb303_hw_f20_c.Tools.SettingsPref;

public class SectionEditActivity extends AppCompatActivity {
    private EditText sectionNo, section_room, sectionTime;
    private Button editSection;
    private SectionViewModel sectionViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int Theme = SettingsPref.getTheme(this);
        if(Theme != 0)
        {
            setTheme(Theme);
        }
        setContentView(R.layout.activity_section_edit);
        sectionViewModel =
                new ViewModelProvider(this).get(SectionViewModel.class);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        SectionsM sectionsM = (SectionsM) intent.getSerializableExtra("section_details");

        sectionNo =  findViewById(R.id.edit_section_no);
        section_room =  findViewById(R.id.edit_section_room);
        sectionTime =  findViewById(R.id.edit_section_time);
        editSection = findViewById(R.id.edit_section_save);
        editSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SettingsPref.getEnableSound(getApplicationContext())){
                    final MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.tone);
                    mp.start();
                }
                sectionsM.setSection_id(sectionsM.getSection_id());
                sectionsM.setSection_section_no(sectionNo.getText().toString());
                sectionsM.setSection_room_no(section_room.getText().toString());
                sectionsM.setSection_time(sectionTime.getText().toString());
                sectionViewModel.updateSection(sectionsM);
                finish();
            }
        });

        sectionNo.setText(sectionsM.getSection_section_no());
        section_room.setText(sectionsM.getSection_room_no());
        sectionTime.setText(sectionsM.getSection_time());

    }
}