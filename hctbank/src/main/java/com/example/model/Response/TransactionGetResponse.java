package com.example.model.Response;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class TransactionGetResponse {
    private String name;
    private long acc_id;
    private long trans_refid;
    private double credit;
    private double debit;
    private double avlbalance;
    private Timestamp transaction_date;

    public TransactionGetResponse() {

    }

    public TransactionGetResponse(String name, long acc_id, long trans_refid, double credit, double debit, double avlbalance, Timestamp transaction_date) {
        this.name = name;
        this.acc_id = acc_id;
        this.trans_refid = trans_refid;
        this.credit = credit;
        this.debit = debit;
        this.avlbalance = avlbalance;
        this.transaction_date = transaction_date;
    }
}
