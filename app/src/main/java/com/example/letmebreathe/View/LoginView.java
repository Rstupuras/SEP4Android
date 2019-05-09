package com.example.letmebreathe.View;

import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.letmebreathe.models.Account;
import com.example.letmebreathe.R;
import com.example.letmebreathe.viewModels.LoginViewModel;

import java.util.List;

public class LoginView extends AppCompatActivity {
    private EditText username;
    private EditText password;

    private TextView textas;
    private Button loginButton;
    private LoginViewModel loginViewModel;
    LinearLayout layout;
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);


        loginButton = findViewById(R.id.loginButton);
        layout = findViewById(R.id.spinnerContainer);
        progress = findViewById(R.id.progressBar1);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        loginViewModel.init();
        loginViewModel.getAccounts().observe(this, new Observer<List<Account>>() {
            @Override
            public void onChanged(@Nullable List<Account> accounts) {

            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                layout.setVisibility(View.VISIBLE);
                progress.setVisibility(View.VISIBLE);
                Account response = loginViewModel.checkLogin(String.valueOf(username.getText()), String.valueOf(password.getText()));
                if (response == null) {
                    layout.setVisibility(View.GONE);
                    progress.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "DENIED", Toast.LENGTH_LONG).show();
                    return;
                }
                if (response.isAdmin()) {
                    Intent startAdminActivity = new Intent(LoginView.this, AllUsersActivity.class);
                    startAdminActivity.putExtra("loggedAdminAccount", response);
                    LoginView.this.startActivityForResult(startAdminActivity, 0);

                }
                if (!response.isAdmin()) {
                    Intent startUserActivity = new Intent(LoginView.this, AllClassroomsActivity.class);
                    startUserActivity.putExtra("userAccount", response);
                    LoginView.this.startActivity(startUserActivity);
                }


            }
        });

    }


    @Override
    protected void onResume() {
        layout.setVisibility(View.GONE);
        progress.setVisibility(View.GONE);
        super.onResume();
    }
}
