package com.example.letmebreathe.View;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.letmebreathe.Model.Account;
import com.example.letmebreathe.R;
import com.example.letmebreathe.ViewModel.LoginViewModel;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;

    private TextView textas;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        textas = findViewById(R.id.textelis);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        loginViewModel.init();
        loginViewModel.getAccounts().observe(this, new Observer<List<Account>>() {
            @Override
            public void onChanged(@Nullable List<Account> accounts) {

            }
        });

    }

    public void login(View v) {
        ArrayList<Account> accounts = (ArrayList<Account>) loginViewModel.getAccounts().getValue();
        String name;
        String pass;
        Account account;
        name = String.valueOf(username.getText());
        pass = String.valueOf(password.getText());
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println(i);
            account = accounts.get(i);

            if (name.equals(account.getUserName()) && pass.equals(account.getPassword())) {
                Toast.makeText(this, "CONFIRMED", Toast.LENGTH_LONG).show();
                return;
            } else {
                Toast.makeText(this, "DENIED", Toast.LENGTH_LONG).show();
            }
        }
    }

}
