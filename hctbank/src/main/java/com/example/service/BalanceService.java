package com.example.service;

import com.example.model.AccBalance;
import com.example.model.CusAccMap;
import com.example.model.Response.BalanceResponse;
import com.example.model.Response.Exceptionresponse;
import com.example.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BalanceService {

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

    public Object getBalanceByCustId(long id) {
        try{
            CusAccMap cap=cusAccMapRepo.findByCustId(id);
            Optional<AccBalance> ab=accBalanceRepo.findById(cap.getAcc_id());
            if(ab.isPresent())
                return new BalanceResponse(cap.getAcc_id(),ab.get().getBalance());
            else
                return new Exceptionresponse("account details not found","hct404");
        }
        catch (Exception e){
            return new Exceptionresponse("customer details not found","hct404");
        }

    }

    public Object getBalanceByAccId(long acc_id) {
        try {
            Optional<AccBalance> ab=accBalanceRepo.findById(acc_id);
            if(ab.isPresent())
                return new BalanceResponse(acc_id,ab.get().getBalance());
            else
                return new Exceptionresponse("customer details not found","hct404");
        }
        catch (Exception e){
            return new Exceptionresponse("customer details not found","hct404");
        }

    }
}
