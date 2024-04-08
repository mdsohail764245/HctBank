package com.example.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "accbalance")
public class AccBalance {
    @Id
    long accId;
    double balance;

    @OneToOne(mappedBy = "accBalance")
    CusAccMap cusAccMap;


}