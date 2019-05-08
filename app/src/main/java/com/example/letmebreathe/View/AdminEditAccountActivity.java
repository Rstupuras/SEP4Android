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
import android.widget.Toast;

import com.example.letmebreathe.BR;
import com.example.letmebreathe.R;
import com.example.letmebreathe.databinding.ActivityAdminEditAccountBinding;
import com.example.letmebreathe.models.Account;
import com.example.letmebreathe.viewModels.EditAccountViewModel;

public class AdminEditAccountActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private EditAccountViewModel editAccountViewModel;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    private Account loggedAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_account);
        ActivityAdminEditAccountBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_admin_edit_account);
        binding.setLifecycleOwner(AdminEditAccountActivity.this);
        editAccountViewModel = ViewModelProviders.of(this).get(EditAccountViewModel.class);
        editAccountViewModel.init();
        binding.setVariable(BR.data, editAccountViewModel);


        configureToolbar();
        Intent previousIntent = getIntent();
        Bundle data = previousIntent.getExtras();
        loggedAccount = (Account) data.getSerializable("loggedAdminAccount");
        editAccountViewModel.setAccount(loggedAccount.getUserName());
        editAccountViewModel.updated.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if(aBoolean==null)
                {
                    return;
                }
                if (aBoolean) {
                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Passwords do not match or password too short", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    public void configureToolbar() {

        toolbar = findViewById(R.id.toolbar_admin_edit);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout_admin_edit);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle
                (this, drawerLayout, toolbar, R.string.second, R.string.third);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_admin_edit);
        navigationView.setNavigationItemSelectedListener(this);

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.drawerAllUsers:
                Intent allClassroomsIntent = new Intent(AdminEditAccountActivity.this, AllUsersActivity.class);
                allClassroomsIntent.putExtra("loggedAdminAccount", loggedAccount);
                startActivity(allClassroomsIntent);
                break;
            case R.id.drawerAdminEditAccount:
                break;
            case R.id.drawerCreateAccount:
                Intent createAccountIntent = new Intent(AdminEditAccountActivity.this, CreateAccountActivity.class);
                createAccountIntent.putExtra("loggedAdminAccount", loggedAccount);
                startActivity(createAccountIntent);
                break;
            case R.id.drawerLogOut:
                Intent intent = new Intent(getApplicationContext(), LoginView.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout_admin_edit);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
