package com.example.model;

import java.util.Optional;

public class CustomerDetailedAddress {
    private String name;
    private long phone;
    //private long address_id;
    private String email;
    private String country;
    private String city;
    private String addresslane;
    private long pin;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddresslane() {
        return addresslane;
    }

    public void setAddresslane(String addresslane) {
        this.addresslane = addresslane;
    }

    public long getPin() {
        return pin;
    }

    public void setPin(long pin) {
        this.pin = pin;
    }
}
