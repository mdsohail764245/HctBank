package com.example.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "cusaccmap")
public class CusAccMap {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    private AccBalance accBalance;
    @OneToOne(cascade = CascadeType.ALL)
    private CustDetails custDetails;

    public CusAccMap() {

    }
}
