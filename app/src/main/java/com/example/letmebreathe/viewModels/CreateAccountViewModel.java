package com.example.letmebreathe.viewModels;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.DataBindingUtil;

import com.example.letmebreathe.R;
import com.example.letmebreathe.Repository.AccountRepository;
import com.example.letmebreathe.View.CreateAccountActivity;
import com.example.letmebreathe.databinding.ActivityCreateAccountBinding;
import com.example.letmebreathe.models.Account;


public class CreateAccountViewModel extends ViewModel {

    private Account account;
    private AccountRepository repo;
    public MutableLiveData<Boolean> updated;
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



        if (account.getPassword().equalsIgnoreCase(account.getPasswordToConfirm()) && account.getPassword().length() > 5 && account.getPasswordToConfirm().length() > 5) {
            repo.addAccount(account);
            updated.setValue(true);

        } else {
            updated.setValue(false);
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
