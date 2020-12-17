package com.example.baitiwb303_hw_f20_c.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.example.baitiwb303_hw_f20_c.Models.AccountM;
import com.example.baitiwb303_hw_f20_c.R;
import com.example.baitiwb303_hw_f20_c.activity.ui.SettingActivity;
import com.example.baitiwb303_hw_f20_c.activity.ui.course.CreateCourseActivity;
import com.example.baitiwb303_hw_f20_c.activity.ui.dr.CreateDrActivity;
import com.example.baitiwb303_hw_f20_c.activity.ui.section.CreateSectionActivity;
import com.example.baitiwb303_hw_f20_c.activity.ui.student.CreateStudentActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private Toolbar toolbar;
    int currentFragment = 0;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        if (item.getItemId() == R.id.action_settings) {
            Intent intent = new Intent(this, SettingActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentFragment == 0){
                    Intent intent = new Intent(MainActivity.this, CreateSectionActivity.class);
                    startActivity(intent);
                }
                else if(currentFragment == 1){
                    Intent intent = new Intent(MainActivity.this, CreateStudentActivity.class);
                    startActivity(intent);
                }
                else if(currentFragment == 2){
                    Intent intent = new Intent(MainActivity.this, CreateCourseActivity.class);
                    startActivity(intent);
                }
                else if(currentFragment == 3){
                    Intent intent = new Intent(MainActivity.this, CreateDrActivity.class);
                    startActivity(intent);
                }
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_section, R.id.nav_student, R.id.nav_course, R.id.nav_dr, R.id.nav_enrollment)
                .setDrawerLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if(destination.getId() == R.id.nav_section){
                currentFragment = 0;
                fab.setVisibility(View.VISIBLE);
            }
            else if(destination.getId() == R.id.nav_student){
                currentFragment = 1;
                fab.setVisibility(View.VISIBLE);
            }
            else if(destination.getId() == R.id.nav_course){
                currentFragment = 2;
                fab.setVisibility(View.VISIBLE);
            }
            else if(destination.getId() == R.id.nav_dr){
                currentFragment = 3;
                fab.setVisibility(View.VISIBLE);
            }
            else if(destination.getId() == R.id.nav_enrollment){
                currentFragment = 4;
                fab.setVisibility(View.GONE);
            }
        });
        Intent intent = getIntent();
        AccountM accountM = (AccountM) intent.getSerializableExtra("LOGIN_INFO");
        View hView = navigationView.getHeaderView(0);
        TextView nav_full_name = hView.findViewById(R.id.nav_bar_full_name);
        TextView nav_user_name = hView.findViewById(R.id.nav_bar_username);
        String full_name = accountM.getFirst_name() + " " + accountM.getLast_name();
        nav_full_name.setText(full_name);
        nav_user_name.setText(accountM.getUser_name());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}