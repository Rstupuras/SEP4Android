package com.example.letmebreathe.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.letmebreathe.R;
import com.example.letmebreathe.models.Account;


import java.util.ArrayList;

public class AccountRecyclerAdapter extends RecyclerView.Adapter<AccountRecyclerAdapter.ViewHolder> {
    final private AccountRecyclerAdapter.OnListItemClickListener onListItemClickListener;
    private ArrayList<Account> accounts;
    private Context context;

    public AccountRecyclerAdapter(Context context, ArrayList<Account> accounts, OnListItemClickListener onListItemClickListener) {
        this.onListItemClickListener = onListItemClickListener;
        this.accounts = accounts;
        this.context = context;
    }

    @NonNull
    @Override
    public AccountRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.account_list_item, viewGroup, false);
        return new AccountRecyclerAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull AccountRecyclerAdapter.ViewHolder viewHolder, int i) {
        viewHolder.username.setText(accounts.get(i).getUserName());
    }

    @Override
    public int getItemCount() {
        return accounts.size();
    }


    public ArrayList<Account> getAccountList() {
        return accounts;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView username;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.accountListItem);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onListItemClickListener.onListItemClick(getAdapterPosition());
        }
    }

    public interface OnListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    public void clear() {
        accounts.clear();
    }

    public void addAll(ArrayList<Account> accountList) {
        accounts.addAll(accountList);
    }

    public void removeItem(int position) {
        accounts.remove(position);
    }
}
