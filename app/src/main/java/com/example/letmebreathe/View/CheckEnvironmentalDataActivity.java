package com.example.letmebreathe.View;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.letmebreathe.BR;
import com.example.letmebreathe.R;
import com.example.letmebreathe.databinding.EvironmentalDataAcitivityBinding;
import com.example.letmebreathe.models.EnvironmentalData;
import com.example.letmebreathe.viewModels.CheckEnvironmentalDataViewModel;

import java.util.ArrayList;

public class CheckEnvironmentalDataActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private CheckEnvironmentalDataViewModel checkEnvironmentalDataViewModel;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    private ArrayList<EnvironmentalData> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EvironmentalDataAcitivityBinding binding = DataBindingUtil.setContentView(this, R.layout.evironmental_data_acitivity);
        binding.setLifecycleOwner(this);

        checkEnvironmentalDataViewModel = ViewModelProviders.of(this).get(CheckEnvironmentalDataViewModel.class);
        checkEnvironmentalDataViewModel.init();
        binding.setVariable(BR.data, checkEnvironmentalDataViewModel);
        configureToolbar();

    }

    public void configureToolbar() {
        setContentView(R.layout.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layoutas);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle
                (this, drawerLayout, toolbar, R.string.second, R.string.third);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.drawerEditAccount:
                Intent myIntent = new Intent(CheckEnvironmentalDataActivity.this, EditAccountActivity.class);
                CheckEnvironmentalDataActivity.this.startActivity(myIntent);
                break;
            case R.id.drawerSettings:
                Intent myIntent1 = new Intent(CheckEnvironmentalDataActivity.this, SettingsActivity.class);
                CheckEnvironmentalDataActivity.this.startActivity(myIntent1);
                break;
            default:
                Toast.makeText(this, "Nothing", Toast.LENGTH_LONG).show();
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layoutas);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
