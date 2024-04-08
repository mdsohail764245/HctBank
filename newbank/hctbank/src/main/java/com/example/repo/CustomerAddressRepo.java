package com.example.repo;

import com.example.model.CustAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerAddressRepo extends JpaRepository<CustAddress,Integer> {

}
