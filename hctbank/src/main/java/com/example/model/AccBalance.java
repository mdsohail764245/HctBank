package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "accbalance")
public class AccBalance {
    @Id
    private long acc_id;
    private double balance;

    public AccBalance() {

    }

    public AccBalance(long acc_id, double balance) {
        this.acc_id = acc_id;
        this.balance = balance;
    }
}
