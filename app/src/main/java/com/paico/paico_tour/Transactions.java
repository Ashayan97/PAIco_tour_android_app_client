package com.paico.paico_tour;

import java.util.Date;

public class Transactions {
    private Date date;
    private String id;
    private String amount;
    private String title;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Transactions(Date date, String id, String amount, String title) {
        this.date = date;
        this.id = id;
        this.amount = amount;
        this.title = title;
    }
}
