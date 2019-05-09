package com.example.letmebreathe.View;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.example.letmebreathe.BR;
import com.example.letmebreathe.R;

import com.example.letmebreathe.databinding.ActivityAdminEditTeacherAccountBinding;
import com.example.letmebreathe.models.Account;
import com.example.letmebreathe.viewModels.AdminEditTeacherAccountViewModel;

public class AdminEditTeacherAccountActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private AdminEditTeacherAccountViewModel adminEditTeacherAccountViewModel;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    private Account loggedAccount;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_teacher_account);
        ActivityAdminEditTeacherAccountBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_admin_edit_teacher_account);
        binding.setLifecycleOwner(AdminEditTeacherAccountActivity.this);
        adminEditTeacherAccountViewModel = ViewModelProviders.of(this).get(AdminEditTeacherAccountViewModel.class);
        adminEditTeacherAccountViewModel.init();
        binding.setVariable(BR.data, adminEditTeacherAccountViewModel);


        configureToolbar();
        Intent previousIntent = getIntent();
        Bundle data = previousIntent.getExtras();
        loggedAccount = (Account) data.getSerializable("loggedAdminAccount");
        position = data.getInt("account");
        adminEditTeacherAccountViewModel.setAccount(position);
        adminEditTeacherAccountViewModel.updated.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean == null) {
                    return;
                }
                if (aBoolean) {
                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Passwords do not match or password too short", Toast.LENGTH_LONG).show();
                }
            }
        });

        adminEditTeacherAccountViewModel.showConfirmDeleteWindow.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean == null) {
                    return;
                }
                if (aBoolean) {
                    final AlertDialog alertDialog = new AlertDialog.Builder(AdminEditTeacherAccountActivity.this).create(); //Read Update

                    alertDialog.setMessage("Do you really want to delete account?");
                    alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            adminEditTeacherAccountViewModel.deleteAccount();
                            Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AdminEditTeacherAccountActivity.this, AllUsersActivity.class);
                            intent.putExtra("loggedAdminAccount", loggedAccount);
                            intent.putExtra("position", position);
                            intent.putExtra("deleted", true);
                            setResult(Activity.RESULT_OK, intent);
                            finish();

                        }
                    });
                    alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                        @Override
                        public void onShow(DialogInterface arg0) {
                            alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.RED);

                        }
                    });

                    alertDialog.show();
                    alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            adminEditTeacherAccountViewModel.dismissConfirmDeleteWindow();
                        }
                    });

                }
            }
        });

    }

    public void configureToolbar() {

        toolbar = findViewById(R.id.toolbar_admin_edit_teacher);
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
                Intent allClassroomsIntent = new Intent(AdminEditTeacherAccountActivity.this, AllUsersActivity.class);
                allClassroomsIntent.putExtra("loggedAdminAccount", loggedAccount);
                startActivity(allClassroomsIntent);
                break;
            case R.id.drawerAdminEditAccount:
                Intent editAdminAccountsIntent = new Intent(AdminEditTeacherAccountActivity.this, AdminEditAccountActivity.class);
                editAdminAccountsIntent.putExtra("loggedAdminAccount", loggedAccount);
                startActivity(editAdminAccountsIntent);
                break;
            case R.id.drawerCreateAccount:
                Intent createAccountIntent = new Intent(AdminEditTeacherAccountActivity.this, CreateAccountActivity.class);
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
