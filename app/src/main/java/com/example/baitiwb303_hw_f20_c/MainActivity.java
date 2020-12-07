package com.example.baitiwb303_hw_f20_c;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.baitiwb303_hw_f20_c.database.SvuDpHelper;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SvuDpHelper dbHelper = new SvuDpHelper(this);
    }
}