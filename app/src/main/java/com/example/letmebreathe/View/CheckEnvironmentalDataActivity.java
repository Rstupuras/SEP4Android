package com.example.letmebreathe.View;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;


import com.example.letmebreathe.BR;
import com.example.letmebreathe.R;

import com.example.letmebreathe.WebAPI.API;
import com.example.letmebreathe.databinding.ActivityCheckEnvironmentalDataBinding;
import com.example.letmebreathe.models.Account;
import com.example.letmebreathe.models.EnvironmentalData;
import com.example.letmebreathe.viewModels.CheckEnvironmentalDataViewModel;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CheckEnvironmentalDataActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private CheckEnvironmentalDataViewModel checkEnvironmentalDataViewModel;

    private ArrayList<EnvironmentalData> data;

    private Account userAccount;
    LinearLayout layout;
    Toolbar toolbar;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCheckEnvironmentalDataBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_check_environmental_data);
        binding.setLifecycleOwner(CheckEnvironmentalDataActivity.this);
        checkEnvironmentalDataViewModel = ViewModelProviders.of(this).get(CheckEnvironmentalDataViewModel.class);
        checkEnvironmentalDataViewModel.init();
        binding.setVariable(BR.data, checkEnvironmentalDataViewModel);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        int id = extras.getInt("environmentalData");

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String preferedTemperature = sharedPref.getString("temperature_preference", "Celsius");

        if (preferedTemperature.equals("Celsius")) {
            checkEnvironmentalDataViewModel.setData(id);
        }
        if (preferedTemperature.equals("Fahrenheit")){
            checkEnvironmentalDataViewModel.setDataFahrenheit(id);
        }
        configureToolbar();
        Intent loginIntent = getIntent();
        Bundle data = loginIntent.getExtras();
        userAccount = (Account) data.getSerializable("userAccount");

        layout = findViewById(R.id.spinnerContainerCheck);

    }


    public void configureToolbar() {
//        setContentView(R.layout.drawer_layout_user);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle
                (this, drawerLayout, toolbar, R.string.second, R.string.third);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(this);
        //setContentView(R.layout.activity_allclassrooms);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.drawerAllClassrooms:
                layout.setVisibility(View.VISIBLE);
                Intent allClassroomsIntent = new Intent(CheckEnvironmentalDataActivity.this, AllClassroomsActivity.class);
                allClassroomsIntent.putExtra("userAccount", userAccount);
                startActivity(allClassroomsIntent);
                break;
            case R.id.drawerTeacherEditAccount:
                Intent editAccountsIntent = new Intent(CheckEnvironmentalDataActivity.this, TeacherEditAccountActivity.class);
                editAccountsIntent.putExtra("userAccount", userAccount);
                startActivity(editAccountsIntent);
                break;
            case R.id.drawerSettings:
                Intent settingsIntent = new Intent(CheckEnvironmentalDataActivity.this, SettingsActivity.class);
                startActivity(settingsIntent);
                return true;
            case R.id.drawerLogOut:
                Intent intent = new Intent(getApplicationContext(), LoginView.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onResume() {
        layout.setVisibility(View.GONE);
        super.onResume();
    }


}



