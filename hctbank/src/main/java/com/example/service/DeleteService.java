package com.example.service;

import com.example.model.CusAccMap;
import com.example.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteService {
    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    CustomerAddressRepo customerAddressRepo;
    @Autowired
    AccBalanceRepo accBalanceRepo;
    @Autowired
    CusAccMapRepo cusAccMapRepo;
    @Autowired
    TransactionRepo transactionRepo;

    public String deleteCustomer(long custId) {
        long aid=customerRepo.findByAddressId(custId);
        customerRepo.deleteById(custId);
        customerAddressRepo.deleteById((int) aid);
        CusAccMap cam=cusAccMapRepo.findByCustId(custId);
        cusAccMapRepo.deleteById(cam.getAcc_id());
        accBalanceRepo.deleteById(cam.getAcc_id());
        return "deleted";
    }
}
