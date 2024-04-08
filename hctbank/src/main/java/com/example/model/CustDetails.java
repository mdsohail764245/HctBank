package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "custdetails")
public class CustDetails {
    @Id
    private long cust_id;
    private String name;
    private long phone;
    private long address_id;
    private String email;
    private Timestamp Created;
    private Timestamp Updated;

    public CustDetails() {

    }

    public CustDetails(long cust_id, String name, long phone, long address_id, String email, Timestamp created, Timestamp updated) {
        this.cust_id = cust_id;
        this.name = name;
        this.phone = phone;
        this.address_id = address_id;
        this.email = email;
        Created = created;
        Updated = updated;
    }

    public CustDetails(String name, long phone, long address_id, String email, Timestamp updated) {
        this.name = name;
        this.phone = phone;
        this.address_id = address_id;
        this.email = email;
        Updated = updated;
    }
}
