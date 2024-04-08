package com.example.repo;

import com.example.model.CustDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<CustDetails,Long> {

    @Query (value = " select address_id from CustDetails where cust_id=:custId ",nativeQuery = true)
    Long findByAddressId(long custId);
}
