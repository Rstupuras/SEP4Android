package com.example.letmebreathe.viewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.letmebreathe.Repository.AccountRepository;
import com.example.letmebreathe.models.Account;

import java.util.List;

public class EditAccountViewModel extends ViewModel {


    private Account account;
    private MutableLiveData<List<Account>> accountList;
    private AccountRepository repo;
    public MutableLiveData<Boolean> updated;

    private String passwordToConfirm;

    public void init() {
        if (accountList != null) {
            return;
        }
        repo = AccountRepository.getInstance();
        accountList = repo.getAccounts();
        updated = new MutableLiveData<>();
        updated.setValue(null);
    }

    public LiveData<List<Account>> getAccountList() {
        return accountList;
    }

    //    public String getUsername() {
//        return username;
//    }
//
    public void setAccount(int id) {
        account = accountList.getValue().get(id);
    }


    public void setAccount(String username) {
        for (Account a : accountList.getValue()
        ) {
            if (a.getUserName().equalsIgnoreCase(username)) {
                account = a;
                return;
            }
        }

    }


    public void updateAccount() {
        if (account.getPassword().equalsIgnoreCase(passwordToConfirm)) {
            repo.updateAccount(account);
            updated.setValue(true);

        }
        else {
            updated.setValue(false);
        }
    }


    public Account getAccount() {

        return account;
    }


    public void setAccount(Account account) {
        this.account = account;
    }

    public String getPasswordToConfirm() {
        return passwordToConfirm;
    }

    public void setPasswordToConfirm(String passwordToConfirm) {
        this.passwordToConfirm = passwordToConfirm;
    }

}
