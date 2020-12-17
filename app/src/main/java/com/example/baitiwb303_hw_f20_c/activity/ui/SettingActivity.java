package com.example.baitiwb303_hw_f20_c.activity.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.example.baitiwb303_hw_f20_c.R;
import com.example.baitiwb303_hw_f20_c.Tools.SettingsPref;
import com.example.baitiwb303_hw_f20_c.activity.LoginActivity;

public class SettingActivity extends AppCompatActivity {
    private RadioGroup radioGroup;
    private Switch enable_sound;
    private  boolean enableSound = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        radioGroup = findViewById(R.id.radioGroup);
        enable_sound = findViewById(R.id.enable_sound);
        radioGroup.clearCheck();
        enable_sound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                enableSound = b;
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

            }
        });
    }

    public void onSubmit(View v) {
        SettingsPref.setEnableSound(enableSound,this);
        RadioButton rb = (RadioButton) radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
        if (radioGroup.indexOfChild(rb) == 0) {
            SettingsPref.setTheme(R.style.FirstTheme,this);
        } else if (radioGroup.indexOfChild(rb) == 1) {
            SettingsPref.setTheme(R.style.SecondTheme,this);
        } else if (radioGroup.indexOfChild(rb) == 2) {
            SettingsPref.setTheme(R.style.ThirdTheme,this);
        } else if (radioGroup.indexOfChild(rb) == 3) {
            SettingsPref.setTheme(R.style.fourthTheme,this);
        } else if (radioGroup.indexOfChild(rb) == 4) {
            SettingsPref.setTheme(R.style.FifthTheme,this);
        }
        Intent mStartActivity = new Intent(this, LoginActivity.class);
        int mPendingIntentId = 123456;
        PendingIntent mPendingIntent = PendingIntent.getActivity(this, mPendingIntentId, mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager mgr = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
        System.exit(0);
        Toast.makeText(this, rb.getText(), Toast.LENGTH_SHORT).show();
    }
}