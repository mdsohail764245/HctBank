package com.example.model.Response;

import lombok.Data;

@Data
public class BalanceResponse {
    private long acc_id;
    private double avlbalance;

    public BalanceResponse(long acc_id, double avlbalance) {
        this.acc_id = acc_id;
        this.avlbalance = avlbalance;
    }
}
