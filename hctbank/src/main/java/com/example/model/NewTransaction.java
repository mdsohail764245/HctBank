package com.example.model;

public class NewTransaction {
    private long acc_id;
    private long to_acc_id;
    private String type;
    private double amount;

    public long getAcc_id() {
        return acc_id;
    }

    public void setAcc_id(long acc_id) {
        this.acc_id = acc_id;
    }

    public long getTo_acc_id() {
        return to_acc_id;
    }

    public void setTo_acc_id(long to_acc_id) {
        this.to_acc_id = to_acc_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
