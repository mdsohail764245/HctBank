package com.example.repo;

import com.example.model.CusAccMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CusAccMapRepo extends JpaRepository<CusAccMap,Long> {
    ;

    @Query(value = "select * from CusAccMap cam where cam.cust_id=:id " ,nativeQuery = true)
    CusAccMap findByCustId(long id);
}
