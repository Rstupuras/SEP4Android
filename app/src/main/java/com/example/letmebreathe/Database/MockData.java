package com.example.letmebreathe.Database;

import com.example.letmebreathe.models.Account;
import com.example.letmebreathe.models.EnvironmentalData;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MockData {
    private static MockData instance;
    private ArrayList<Account> accounts = new ArrayList<>();


    private ArrayList<EnvironmentalData> data = new ArrayList<>();

    public MockData() {
        generateAccounts();
        generateEnvironmentalData();
    }

    public static MockData getInstance() {
        if (instance == null) {
            instance = new MockData();
        }
        return instance;
    }

    public ArrayList<Account> getAccounts() {

        return accounts;

    }

    public ArrayList<EnvironmentalData> getData() {
        return data;
    }


    private String randomString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();

        return generatedString;
    }

    private void generateAccounts() {
        Account account = new Account();
        account.setUserName("user");
        account.setPassword("user");
        account.setAdmin(false);
        accounts.add(account);
        Account account2 = new Account();
        account2.setUserName("admin");
        account2.setPassword("admin");
        account2.setAdmin(true);
        accounts.add(account2);
        for (int i = 0; i < 10; i++) {
            Account account1 = new Account();
            account1.setUserName(randomString());
            account1.setPassword(randomString());
            accounts.add(account1);
        }

    }

    public void generateEnvironmentalData() {

        for (int i = 0; i < 20; i++) {
            EnvironmentalData oneData = new EnvironmentalData();
            oneData.setCO2(randomDouble());
            oneData.setTemperature(randomDouble());
            oneData.setLocation(randomString());
            data.add(oneData);
        }
    }

    public double randomDouble() {
        Random r = new Random();
        double randomValue = ThreadLocalRandom.current().nextDouble(15, 25);
        return randomValue;
    }
}
