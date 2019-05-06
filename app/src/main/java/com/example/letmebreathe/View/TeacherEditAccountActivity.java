package com.example.letmebreathe.View;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.letmebreathe.BR;
import com.example.letmebreathe.R;
import com.example.letmebreathe.databinding.ActivityEditAccountBinding;
import com.example.letmebreathe.viewModels.EditAccountViewModel;

public class TeacherEditAccountActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private EditAccountViewModel editAccountViewModel;
    Toolbar toolbar;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);
        ActivityEditAccountBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_account);
        binding.setLifecycleOwner(TeacherEditAccountActivity.this);

        configureToolbar();

        editAccountViewModel = ViewModelProviders.of(this).get(EditAccountViewModel.class);
        editAccountViewModel.init();
        binding.setVariable(BR.data, editAccountViewModel);


    }

    public void configureToolbar() {

        toolbar = findViewById(R.id.toolbar_teacher_edit);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout_teacher_edit);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle
                (this, drawerLayout, toolbar, R.string.second, R.string.third);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_teacher_edit);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.drawerAllClassrooms:
                Intent allClassroomsIntent = new Intent(TeacherEditAccountActivity.this, AllClassroomsActivity.class);
                startActivity(allClassroomsIntent);
                break;
            case R.id.drawerTeacherEditAccount:
                break;
            case R.id.drawerSettings:
                Intent settingsIntent = new Intent(TeacherEditAccountActivity.this, SettingsActivity.class);
                startActivity(settingsIntent);
                return true;

        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout_teacher_edit);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
