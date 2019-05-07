package com.example.letmebreathe.viewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.letmebreathe.models.Account;
import com.example.letmebreathe.Repository.AccountRepository;

import java.util.ArrayList;
import java.util.List;

public class LoginViewModel extends ViewModel {
    private MutableLiveData<List<Account>> accounts;
    private AccountRepository accountRepository;
    private ArrayList<Account> accountList;

    public void init() {
        if (accounts != null) {
            return;
        }
        accountRepository = AccountRepository.getInstance();
        accounts = accountRepository.getAccounts();

    }

    public LiveData<List<Account>> getAccounts() {

        return accounts;
    }


    public Account checkLogin(String username, String password) {
        for (Account a : accounts.getValue()
        ) {
            if (a.getUserName().equalsIgnoreCase(username) && a.getPassword().equalsIgnoreCase(password)) {
                return a;
            }
        }
        return null;
    }
}
