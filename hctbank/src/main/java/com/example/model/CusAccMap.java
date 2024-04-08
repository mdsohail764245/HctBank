package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "cusaccmap")
public class CusAccMap {
    @Id
    private long acc_id;
    private long cust_id;

    public CusAccMap() {
    }

    public CusAccMap(long acc_id, long cust_id) {
        this.acc_id = acc_id;
        this.cust_id = cust_id;
    }
}
