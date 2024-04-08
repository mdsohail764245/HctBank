package com.example.model.Response;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class CustomerDetailsResponse {
    private String name;
    private long phone;
    private String email;
    private long cust_id;
    private long acc_id;
    private Timestamp created_on;

    public CustomerDetailsResponse() {

    }

    public CustomerDetailsResponse(String name, long phone, String email, long cust_id, long acc_id, Timestamp created_on) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.cust_id = cust_id;
        this.acc_id = acc_id;
        this.created_on = created_on;
    }
}
