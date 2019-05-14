package com.example.letmebreathe.viewModels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.letmebreathe.Repository.AccountRepository;
import com.example.letmebreathe.View.CreateAccountActivity;
import com.example.letmebreathe.models.Account;


public class CreateAccountViewModel extends ViewModel {

    private Account account;
    private AccountRepository repo;
    public MutableLiveData<Integer> updated;
    private CreateAccountActivity createAccountActivity;

    private String passwordToConfirm;

    public void init() {

        account = new Account("", "");
        repo = AccountRepository.getInstance();
        updated = new MutableLiveData<>();
        updated.setValue(null);

    }


    public void setAccount(Account account) {
        this.account = account;
    }


    public void addAccount() {


        if (account.getPassword().equals(account.getPasswordToConfirm()) && account.getPassword().length() > 5 && account.getPasswordToConfirm().length() > 5 && repo.doesNotAccountExists(account.getUserName()) && account.getUserName().length() > 5) {
            repo.addAccount(account);
            updated.setValue(1);
            return;
        }
        if (account.getUserName().length() < 6) {
            updated.setValue(2);
            return;
        }
        if (!(account.getPassword().equals(account.getPasswordToConfirm()))) {
            updated.setValue(3);
            return;
        }
        if (account.getPassword().length() < 6) {
            updated.setValue(4);
            return;
        }
        if (!(repo.doesNotAccountExists(account.getUserName()))) {
            updated.setValue(5);
            return;
        } else {
            updated.setValue(0);
            return;
        }
    }


    public Account getAccount() {

        return account;
    }

    public String getPasswordToConfirm() {
        return passwordToConfirm;
    }

    public void setPasswordToConfirm(String passwordToConfirm) {
        this.passwordToConfirm = passwordToConfirm;
    }


}
