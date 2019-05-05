package com.example.letmebreathe.View;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.letmebreathe.models.Account;
import com.example.letmebreathe.R;
import com.example.letmebreathe.viewModels.LoginViewModel;

import java.util.ArrayList;
import java.util.List;

public class LoginView extends AppCompatActivity {
    private EditText username;
    private EditText password;

    private TextView textas;
    private Button loginButton;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        textas = findViewById(R.id.textelis);
        loginButton = findViewById(R.id.loginButton);

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
                boolean validateLogin = loginViewModel.method(String.valueOf(username.getText()), String.valueOf(password.getText()));
                if (validateLogin) {
                    Intent myIntent = new Intent(LoginView.this, CheckEnvironmentalDataActivity.class);
                    LoginView.this.startActivity(myIntent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"DENIED", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}
