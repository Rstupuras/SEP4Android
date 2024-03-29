package com.example.letmebreathe.Repository;


import android.arch.lifecycle.MutableLiveData;

import com.example.letmebreathe.WebAPI.ApiConsumer;
import com.example.letmebreathe.models.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AccountRepository {
    private static AccountRepository instance;
    private ArrayList<Account> accounts = new ArrayList<>();
    private ApiConsumer data = ApiConsumer.getInstance();

    public static AccountRepository getInstance() {
        if (instance == null) {
            instance = new AccountRepository();
        }
        return instance;
    }

    public MutableLiveData<List<Account>> getAccounts() {
        setAccounts();
        MutableLiveData<List<Account>> data = new MutableLiveData<>();
        data.setValue(accounts);
        return data;
    }

    private void setAccounts() {
        accounts = data.getAccounts();

    }

    public void updateAccount(Account account) {
        data.updateAccount(account);
    }

    public void addAccount(Account account) {
        data.addAccount(account);
    }


    public void deleteAccount(String username) {
        data.deleteAccount(username);
    }

    public boolean compareStrings(String a, String b) {
        return Objects.equals(a, b);
    }

    public boolean doesNotAccountExists(String username) {
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getUserName().equals(username)) {
                return false;
            }
        }
        return true;
    }
}
