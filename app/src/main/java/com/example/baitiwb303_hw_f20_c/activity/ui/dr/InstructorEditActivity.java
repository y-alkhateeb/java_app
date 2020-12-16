package com.example.baitiwb303_hw_f20_c.activity.ui.dr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.baitiwb303_hw_f20_c.Models.InstructorM;
import com.example.baitiwb303_hw_f20_c.R;
import com.example.baitiwb303_hw_f20_c.database.DatabaseHelper;

public class InstructorEditActivity extends AppCompatActivity {
    private EditText firstName, lastName, address, phoneNumber;
    private Button addInstructor;
    private Spinner spinner;
    private DrViewModel DrViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_edit);
        DrViewModel =
                new ViewModelProvider(this).get(DrViewModel.class);
        Intent intent = getIntent();
        InstructorM instructorM = (InstructorM) intent.getSerializableExtra("instructor_details");
        spinner = findViewById(R.id.edit_instructor_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        firstName = findViewById(R.id.edit_instructor_first_name);
        lastName = findViewById(R.id.edit_instructor_last_name);
        phoneNumber = findViewById(R.id.edit_instructor_phone);
        address = findViewById(R.id.edit_instructor_address);
        addInstructor = findViewById(R.id.edit_save_change_instructor);
        addInstructor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                instructorM.setInstructor_id(instructorM.getInstructor_id());
                instructorM.setInstructor_gender(spinner.getSelectedItem().toString());
                instructorM.setInstructor_first_name(firstName.getText().toString());
                instructorM.setInstructor_last_name(lastName.getText().toString());
                instructorM.setInstructor_address(address.getText().toString());
                instructorM.setInstructor_mobile(phoneNumber.getText().toString());
                DrViewModel.updateInstructor(instructorM);
                finish();
            }
        });

        firstName.setText(instructorM.getInstructor_first_name());
        lastName.setText(instructorM.getInstructor_last_name());
        phoneNumber.setText(instructorM.getInstructor_mobile());
        address.setText(instructorM.getInstructor_address());
        if(instructorM.getInstructor_gender().equals("Male"))
            spinner.setSelection(0);
        else spinner.setSelection(1);
    }
}