package com.paico.paico_tour.object_classes;

public class User {
    private String name;
    private String balance;
    private String Email;

    public User(String name, String balance, String email) {
        this.name = name;
        this.balance = balance;
        Email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
