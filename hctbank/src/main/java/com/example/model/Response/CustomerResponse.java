package com.example.model.Response;

import lombok.Data;

@Data
public class CustomerResponse {
    
    private String name;
    private long cus_id;
    private long acc_id;
    private double balance;

    public CustomerResponse(String name, long cus_id, long acc_id, double balance) {
        this.name = name;
        this.cus_id = cus_id;
        this.acc_id = acc_id;
        this.balance = balance;
    }
}
