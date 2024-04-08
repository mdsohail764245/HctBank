package com.example.service;

import com.example.controller.GenerateRandom;
import com.example.model.*;
import com.example.model.Response.*;
import com.example.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

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
    public List<CustomerDetailsResponse> getCustomers() {

          try{
              List<CustDetails> list= customerRepo.findAll();
              List<CustomerDetailsResponse> cdrlist=new ArrayList<>();
              for(CustDetails cust:list){
                  CusAccMap cam=cusAccMapRepo.findByCustId(cust.getCust_id());
                  CustomerDetailsResponse cdr=new CustomerDetailsResponse(cust.getName(),cust.getPhone(),cust.getEmail(),cust.getCust_id(), cam.getAcc_id(), cust.getCreated());
                  cdrlist.add(cdr);
              }
              return cdrlist;
          }
          catch (Exception e){
              return null;
          }

    }

    public Object addCustomer(CustomerDetailedAddress cdo) {
        CustomerDetailedAddress cdo1=cdo;
        Field[] fields=cdo1.getClass().getDeclaredFields();
        for(Field f:fields){
            try {
                f.setAccessible(true);
                Object value=f.get(cdo1);
                if(value==null){
                    return new Exceptionresponse("must enter complete details","HCT400");
                }
                System.out.println(value);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        GenerateRandom gr=new GenerateRandom();
        long cid= gr.getRandom();
        long aid= gr.getRandom();
        long acc_id=gr.getRandom();
        Timestamp currentTimestamp = new Timestamp(new Date().getTime());
        CustDetails cd=new CustDetails(cid,cdo.getName(),cdo.getPhone(),aid,cdo.getEmail(),currentTimestamp,currentTimestamp);
        CustAddress ca=new CustAddress(aid,cdo.getCountry(),cdo.getCity(),cdo.getAddresslane(),cdo.getPin(),currentTimestamp);
        customerAddressRepo.save(ca);
        customerRepo.save(cd);

        AccBalance ab=new AccBalance(acc_id,500.00);
        accBalanceRepo.save(ab);

        CusAccMap cam=new CusAccMap(acc_id,cid);
        cusAccMapRepo.save(cam);

        return new CustomerResponse(cd.getName(),cd.getCust_id(),ab.getAcc_id(),ab.getBalance());
    }

    public Object getCustomerById(long id) {
        try{
            Optional<CustDetails> cdget=customerRepo.findById(id);
            CustDetails cd= cdget.get();
            CusAccMap cam=cusAccMapRepo.findByCustId(cd.getCust_id());
            CustomerDetailsResponse cdr=new CustomerDetailsResponse(cd.getName(),cd.getPhone(),cd.getEmail(),cd.getCust_id(), cam.getAcc_id(), cd.getCreated());
            return Optional.of(cdr);
        }
        catch (Exception e){
            return new Exceptionresponse("customer details not found","hct404");
        }

    }



}
