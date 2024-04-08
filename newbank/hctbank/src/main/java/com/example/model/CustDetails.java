package com.example.model;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;


@Entity
@Data
@Table(name = "custdetails")
public class CustDetails {
    @Id
    private long cust_id;

    private String name;
    private long phone;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private CustAddress address;

    private String email;
    private Timestamp Created;
    private Timestamp Updated;

    public CustDetails(long cust_id, String name, long phone, String email, Timestamp created, Timestamp updated) {
        this.cust_id = cust_id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        Created = created;
        Updated = updated;
    }

    public CustDetails() {

    }
}
