package com.example.baitiwb303_hw_f20_c.activity.ui.dr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.baitiwb303_hw_f20_c.Models.InstructorM;
import com.example.baitiwb303_hw_f20_c.R;
import com.example.baitiwb303_hw_f20_c.Tools.SettingsPref;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class CreateDrActivity extends AppCompatActivity {

    private EditText firstName, lastName, address, phoneNumber;
    private Button addInstructor;
    private Spinner spinner;
    private DrViewModel drViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int Theme = SettingsPref.getTheme(this);
        if(Theme != 0)
        {
            setTheme(Theme);
        }
        setContentView(R.layout.activity_create_dr);

        drViewModel =
                new ViewModelProvider(this).get(DrViewModel.class);
        spinner = findViewById(R.id.create_instructor_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        firstName =  findViewById(R.id.create_instructor_first_name);
        lastName =  findViewById(R.id.create_instructor_last_name);
        phoneNumber =  findViewById(R.id.create_instructor_phone);
        address =  findViewById(R.id.create_instructor_address);
        addInstructor =  findViewById(R.id.create_instructor_account);
        addInstructor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SettingsPref.getEnableSound(getApplicationContext())){
                    final MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.tone);
                    mp.start();
                }
                InstructorM instructorM = new InstructorM();
                instructorM.setInstructor_gender(spinner.getSelectedItem().toString());
                instructorM.setInstructor_first_name(firstName.getText().toString());
                instructorM.setInstructor_last_name(lastName.getText().toString());
                instructorM.setInstructor_address(address.getText().toString());
                instructorM.setInstructor_mobile(phoneNumber.getText().toString());

                if (drViewModel.createInstructor(instructorM)){
                    Snackbar.make(v,"You Added a new instructor", BaseTransientBottomBar.LENGTH_SHORT);
                    finish();
                }
                else Snackbar.make(v,"Failed to add a new instructor please try again", BaseTransientBottomBar.LENGTH_SHORT);
            }
        });
    }
}