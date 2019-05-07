package com.example.letmebreathe.View;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.letmebreathe.R;
import com.example.letmebreathe.adapters.AccountRecyclerAdapter;
import com.example.letmebreathe.models.Account;
import com.example.letmebreathe.viewModels.AllAccountsViewModel;

import java.util.ArrayList;
import java.util.List;

public class AllUsersActivity extends AppCompatActivity implements AccountRecyclerAdapter.OnListItemClickListener, NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    private AccountRecyclerAdapter adapter;
    private AllAccountsViewModel allAccountsViewModel;
    Toolbar toolbar;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);

//        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(myToolbar);
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_user);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, myToolbar, R.string.open,R.string.close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();

        configureToolbar();

        recyclerView = findViewById(R.id.rvAccounts);

        allAccountsViewModel = ViewModelProviders.of(this).get(AllAccountsViewModel.class);

        allAccountsViewModel.init();

        allAccountsViewModel.getAccountList().observe(this, new Observer<List<Account>>() {
            @Override
            public void onChanged(@Nullable List<Account> accounts) {
                adapter.notifyDataSetChanged();
            }
        });

        initRecyclerView();
        System.out.println(allAccountsViewModel.getAccountList().getValue().size());
    }

    private void initRecyclerView() {

        adapter = new AccountRecyclerAdapter(AllUsersActivity.this, (ArrayList<Account>) allAccountsViewModel.getAccountList().getValue(), this);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(AllUsersActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onListItemClick(int clickedItemIndex) {
        Intent intent = new Intent(AllUsersActivity.this, TeacherEditAccountActivity.class);
        intent.putExtra("account", clickedItemIndex);
        startActivity(intent);
    }

    public void configureToolbar() {
//        setContentView(R.layout.drawer_layout_user);
        toolbar = findViewById(R.id.toolbar_all_users);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout_all_users);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle
                (this, drawerLayout, toolbar, R.string.second, R.string.third);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_all_users);
        navigationView.setNavigationItemSelectedListener(this);
        //setContentView(R.layout.activity_allclassrooms);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.drawerAllUsers:

                break;
            case R.id.drawerAdminEditAccount:
                Intent editAdminAccountsIntent = new Intent(AllUsersActivity.this, AdminEditAccountActivity.class);
                startActivity(editAdminAccountsIntent);
                break;

        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout_all_users);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
