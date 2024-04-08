package com.example.service;

import com.example.controller.GenerateRandom;
import com.example.model.*;
import com.example.model.Response.CustomerDetailsResponse;
import com.example.model.Response.CustomerResponse;
import com.example.model.Response.Exceptionresponse;
import com.example.repo.AccBalanceRepo;
import com.example.repo.AddressRepo;
import com.example.repo.CusAccMapRepo;
import com.example.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service public class CustomerService {

    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    AddressRepo addressRepo;
    @Autowired
    AccBalanceRepo accBalanceRepo;
    @Autowired
    CusAccMapRepo cusAccMapRepo;

    public CustomerResponse addCustomer(CustomerDetailedAddress cdo) {
        GenerateRandom gr=new GenerateRandom();
        long cid=gr.getRandom();
        long aid=gr.getRandom();
        Timestamp currentTimestamp = new Timestamp(new Date().getTime());
        CustAddress ca=new CustAddress(aid,cdo.getCountry(),cdo.getCity(), cdo.getAddresslane(), cdo.getPin(),currentTimestamp);
        CustDetails cd=new CustDetails(cid,cdo.getName(), cdo.getPhone(),cdo.getEmail(),currentTimestamp,currentTimestamp);
        CustAddress caa = addressRepo.save(ca);
        cd.setAddress(caa);
        long acc_id=gr.getRandom();
        AccBalance ab=new AccBalance();
        ab.setAccId(acc_id);
        ab.setBalance(500.00);
        CustDetails cdd = customerRepo.save(cd);
        AccBalance acd = accBalanceRepo.save(ab);

        CusAccMap cusAccMap=new CusAccMap();
        cusAccMap.setAccBalance(acd);
        cusAccMap.setCustDetails(cdd);

        cusAccMapRepo.save(cusAccMap);

        return new CustomerResponse(cdd.getName(),cdd.getCust_id(),ab.getAccId(),500);
    }


    public Object getCustomerById(long id) {
        try{
            Optional<CustDetails> cdget=customerRepo.findById(id);
            CustDetails cd= cdget.get();
            CusAccMap cam=cusAccMapRepo.findByCustId(cd.getCust_id());
            CustomerDetailsResponse cdr=new CustomerDetailsResponse(cd.getName(),cd.getPhone(),cd.getEmail(),cd.getCust_id(), cam.getAccBalance().getAccId(), cd.getCreated());
            return Optional.of(cdr);
        }
        catch (Exception e){
            return new Exceptionresponse("customer details not found","hct404");
        }

    }

    public List<CustomerDetailsResponse> getCustomers() {

        try{
            List<CustDetails> list= customerRepo.findAll();
            List<CustomerDetailsResponse> cdrlist=new ArrayList<>();
            for(CustDetails cust:list){
                CusAccMap cam=cusAccMapRepo.findByCustId(cust.getCust_id());
                CustomerDetailsResponse cdr=new CustomerDetailsResponse(cust.getName(),cust.getPhone(),cust.getEmail(),cust.getCust_id(), cam.getCustDetails().getCust_id(), cust.getCreated());
                cdrlist.add(cdr);
            }
            return cdrlist;
        }
        catch (Exception e){
            return null;
        }

    }
}
