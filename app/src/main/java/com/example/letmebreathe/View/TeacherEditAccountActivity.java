package com.example.letmebreathe.View;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.letmebreathe.BR;
import com.example.letmebreathe.R;
import com.example.letmebreathe.databinding.ActivityEditAccountBinding;
import com.example.letmebreathe.models.Account;
import com.example.letmebreathe.viewModels.EditAccountViewModel;

public class TeacherEditAccountActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private EditAccountViewModel editAccountViewModel;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    LinearLayout layout;
    private Account userAccount;


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
        Intent allClassroomsIntent = getIntent();
        Bundle data = allClassroomsIntent.getExtras();
        userAccount = (Account) data.getSerializable("userAccount");
        editAccountViewModel.setAccount(userAccount.getUserName());
        editAccountViewModel.updated.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean == null) {
                    return;
                }
                if (aBoolean) {
                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Passwords either do not match or are too short", Toast.LENGTH_LONG).show();
                }
            }
        });
        editAccountViewModel.showConfirmDeleteWindow.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean == null) {
                    return;
                }
                if (aBoolean) {
                    final AlertDialog alertDialog = new AlertDialog.Builder(TeacherEditAccountActivity.this).create(); //Read Update

                    alertDialog.setMessage("Do you really want to delete account?");
                    alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            editAccountViewModel.deleteAccount();
                            Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(TeacherEditAccountActivity.this, LoginView.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                                    | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
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
                            editAccountViewModel.dismissConfirmDeleteWindow();
                        }
                    });

                }
            }
        });

        layout = findViewById(R.id.spinnerContainerEdit);
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
                layout.setVisibility(View.VISIBLE);
                Intent allClassroomsIntent = new Intent(TeacherEditAccountActivity.this, AllClassroomsActivity.class);
                allClassroomsIntent.putExtra("userAccount", userAccount);
                startActivity(allClassroomsIntent);
                break;
            case R.id.drawerTeacherEditAccount:
                break;
            case R.id.drawerSettings:
                Intent settingsIntent = new Intent(TeacherEditAccountActivity.this, SettingsActivity.class);
                startActivity(settingsIntent);
                return true;
            case R.id.drawerLogOut:
                Intent intent = new Intent(getApplicationContext(), LoginView.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout_teacher_edit);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onResume() {
        layout.setVisibility(View.GONE);

        super.onResume();
    }


}
