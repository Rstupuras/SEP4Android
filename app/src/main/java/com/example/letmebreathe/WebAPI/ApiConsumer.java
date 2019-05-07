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


        final ArrayList<Account> accountsToReturn = new ArrayList<>();
        Call<List<Account>> call = api.getAccounts();

        call.enqueue(new Callback<List<Account>>() {

            @Override
            public void onResponse(Call<List<Account>> call, Response<List<Account>> response) {

                List<Account> accountsList = new ArrayList<>();
                accountsList = response.body();
                accountsToReturn.addAll(accountsList);

            }

            @Override
            public void onFailure(Call<List<Account>> call, Throwable t) {
                System.out.println(t.getCause());
            }
        });
        return accountsToReturn;

    }

}
