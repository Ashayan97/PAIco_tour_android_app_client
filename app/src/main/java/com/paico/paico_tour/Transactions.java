package com.paico.paico_tour;

import java.util.Date;

public class Transactions {
    private Date date;
    private String id;
    private String title;
    private String amount;

    public Transactions(Date date, String id, String title, String amount) {
        this.date = date;
        this.id = id;
        this.title = title;
        this.amount = amount;
    }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
