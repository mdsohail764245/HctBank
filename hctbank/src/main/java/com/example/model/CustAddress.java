package com.example.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
@Table(name="custaddress")
public class CustAddress {
    @Id
    private long address_id;
    private String country;
    private String city;
    private String addresslane;
    private long pin;
    private Timestamp lastupdate;

    public CustAddress() {

    }

    public CustAddress(long address_id, String country, String city, String addresslane, long pin, Timestamp lastupdate) {
        this.address_id = address_id;
        this.country = country;
        this.city = city;
        this.addresslane = addresslane;
        this.pin = pin;
        this.lastupdate = lastupdate;
    }
}
