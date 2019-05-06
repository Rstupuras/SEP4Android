package com.example.letmebreathe.viewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.letmebreathe.Repository.AccountRepository;


import com.example.letmebreathe.models.Account;


import java.util.List;

public class AllAccountsViewModel extends ViewModel {
    private MutableLiveData<List<Account>> accountList;
    private AccountRepository repo;

    public void init() {
        if (accountList != null) {
            return;
        }
        repo = AccountRepository.getInstance();
        accountList = repo.getAccounts();
    }

    public LiveData<List<Account>> getAccountList() {
        return accountList;
    }
}
