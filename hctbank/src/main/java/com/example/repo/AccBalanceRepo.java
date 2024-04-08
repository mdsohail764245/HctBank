package com.example.repo;

import com.example.model.AccBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccBalanceRepo extends JpaRepository<AccBalance,Long> {

    @Query(value = "update accbalance set balance=:bal where acc_id=:id ",nativeQuery = true)
    @Modifying
    void updateByAccId(long id,double bal);
}
