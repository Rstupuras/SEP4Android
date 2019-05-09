package com.example.letmebreathe.WebAPI;

import com.example.letmebreathe.Database.MockData;
import com.example.letmebreathe.models.Account;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiConsumer {


    private static ApiConsumer instance;
    private Retrofit retrofit;
    private API api;
    ArrayList<Account> accountsToReturn;
    private ArrayList<Account> accounts = new ArrayList<>();

    public static ApiConsumer getInstance() {
        if (instance == null) {
            instance = new ApiConsumer();
        }
        return instance;
    }

    public ApiConsumer() {
        retrofit = new Retrofit.Builder().baseUrl("https://sep4dataapi.azurewebsites.net/api/").addConverterFactory(GsonConverterFactory.create()).build();
        api = retrofit.create(API.class);

    }

    public ArrayList<Account> getAccounts() {

        final Call<ArrayList<Account>> call = api.getAccounts();
        call.enqueue(new Callback<ArrayList<Account>>() {
            @Override
            public void onResponse(Call<ArrayList<Account>> call, Response<ArrayList<Account>> response) {
                accounts.clear();
                ArrayList<Account> accounts1 = new ArrayList<>();
                accounts1 = response.body();
                accounts.addAll(accounts1);
            }

            @Override
            public void onFailure(Call<ArrayList<Account>> call, Throwable t) {

            }
        });
        return accounts;

    }

    public void updateAccount(Account account) {
        Call<Void> call = api.updateAccount(account.getUserName(), account);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    getAccounts();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void addAccount(Account account) {
        Call<Void> call = api.addAccount(account);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    getAccounts();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void deleteAccount(String username) {
        Call<Void> call = api.deleteAccount(username);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    getAccounts();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

}
