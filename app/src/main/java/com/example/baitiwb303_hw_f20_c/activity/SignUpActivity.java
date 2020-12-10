package com.example.baitiwb303_hw_f20_c.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.baitiwb303_hw_f20_c.R;

public class SignUpActivity extends AppCompatActivity {
    TextView signin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signin = findViewById(R.id.link_login);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                SignUpActivity.this.finish();
                startActivity(intent);
            }
        });
    }
}