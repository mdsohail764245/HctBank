package com.example.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "acctransaction")
public class AccTransaction {
    @Id
    private long trans_id;
    private long trans_refid;

    private long acc_id;
    private  double credit;
    private  double debit;
    private  double avlbalance;
    private Timestamp lastupdated;

    public AccTransaction(long trans_id, long trans_refid, long acc_id, double credit, double debit, double avlbalance, Timestamp lastupdated) {
        this.trans_id = trans_id;
        this.trans_refid = trans_refid;
        this.acc_id = acc_id;
        this.credit = credit;
        this.debit = debit;
        this.avlbalance = avlbalance;
        this.lastupdated = lastupdated;
    }

    public AccTransaction() {

    }
}
