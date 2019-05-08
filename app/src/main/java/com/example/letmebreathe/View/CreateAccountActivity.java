package com.example.letmebreathe.View;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.letmebreathe.BR;
import com.example.letmebreathe.R;
import com.example.letmebreathe.databinding.ActivityCreateAccountBinding;
import com.example.letmebreathe.models.Account;
import com.example.letmebreathe.viewModels.CreateAccountViewModel;
import com.example.letmebreathe.viewModels.EditAccountViewModel;

public class CreateAccountActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private CreateAccountViewModel createAccountViewModel;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    private Account newAccount;
    private Button saveButton;
    private Account loggedAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        ActivityCreateAccountBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_create_account);
        binding.setLifecycleOwner(CreateAccountActivity.this);

        configureToolbar();

        createAccountViewModel = ViewModelProviders.of(this).get(CreateAccountViewModel.class);
        createAccountViewModel.init();
        binding.setVariable(BR.data, createAccountViewModel);


        configureToolbar();

        createAccountViewModel = ViewModelProviders.of(this).get(CreateAccountViewModel.class);
        createAccountViewModel.init();

        createAccountViewModel.updated.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean == null) {
                    return;
                }
                if (aBoolean) {
                    Toast.makeText(getApplicationContext(), "Created successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Intent previousIntent = getIntent();
        Bundle data = previousIntent.getExtras();

        loggedAccount = (Account) data.getSerializable("loggedAdminAccount");

    }


    public void configureToolbar() {

        toolbar = findViewById(R.id.toolbar_create_account);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout_create_account);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle
                (this, drawerLayout, toolbar, R.string.second, R.string.third);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_create_account);
        navigationView.setNavigationItemSelectedListener(this);

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.drawerAllUsers:
                Intent allClassroomsIntent = new Intent(CreateAccountActivity.this, AllUsersActivity.class);
                allClassroomsIntent.putExtra("loggedAdminAccount", loggedAccount);
                startActivity(allClassroomsIntent);
                break;
            case R.id.drawerAdminEditAccount:
                Intent editAdminAccountsIntent = new Intent(CreateAccountActivity.this, AdminEditAccountActivity.class);
                editAdminAccountsIntent.putExtra("loggedAdminAccount", loggedAccount);
                startActivity(editAdminAccountsIntent);
                break;
            case R.id.drawerCreateAccount:
                break;
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout_create_account);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

