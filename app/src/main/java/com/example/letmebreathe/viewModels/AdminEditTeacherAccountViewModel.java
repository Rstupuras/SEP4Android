package com.example.letmebreathe.viewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.letmebreathe.Repository.AccountRepository;
import com.example.letmebreathe.models.Account;

import java.util.List;


public class AdminEditTeacherAccountViewModel extends ViewModel {

    private Account account;
    private MutableLiveData<List<Account>> accountList;
    private AccountRepository repo;
    public MutableLiveData<Boolean> updated;


    public MutableLiveData<Boolean> showConfirmDeleteWindow;

    public void init() {
        if (accountList != null) {
            return;
        }
        repo = AccountRepository.getInstance();
        accountList = repo.getAccounts();
        updated = new MutableLiveData<>();
        updated.setValue(null);
        showConfirmDeleteWindow = new MutableLiveData<>();
        showConfirmDeleteWindow.setValue(null);
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
        account.setPassword("");
    }


    public void setAccount(String username) {
        for (Account a : accountList.getValue()
        ) {
            if (a.getUserName().equalsIgnoreCase(username)) {
                account = a;
                account.setPassword("");
                return;
            }
        }

    }


    public void updateAccount() {
        if (account.getPassword().equalsIgnoreCase(account.getPasswordToConfirm()) && account.getPassword().length() > 5 && account.getPasswordToConfirm().length() > 5) {
            repo.updateAccount(account);
            updated.setValue(true);

        } else {
            updated.setValue(false);
        }
    }


    public Account getAccount() {

        return account;
    }


    public void setAccount(Account account) {
        this.account = account;
    }

    public MutableLiveData<Boolean> getShowConfirmDeleteWindow() {
        return showConfirmDeleteWindow;
    }

    public void setShowConfirmDeleteWindow(MutableLiveData<Boolean> showConfirmDeleteWindow) {
        this.showConfirmDeleteWindow = showConfirmDeleteWindow;
    }

    public void showConfirmDeleteWindow() {
        this.showConfirmDeleteWindow.setValue(true);
    }

    public void dismissConfirmDeleteWindow() {
        this.showConfirmDeleteWindow.setValue(false);
    }


    public void deleteAccount() {
        repo.deleteAccount(account.getUserName());

    }

}
