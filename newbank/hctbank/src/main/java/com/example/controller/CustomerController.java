package com.example.controller;


import com.example.model.*;
import com.example.model.Response.CustomerDetailsResponse;
import com.example.model.Response.CustomerResponse;
import com.example.model.Response.Exceptionresponse;
import com.example.repo.AddressRepo;
import com.example.repo.CustomerRepo;
import com.example.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("hct")
public class CustomerController {

    @Autowired
    CustomerService customerService;
    @Autowired
    BalanceService balanceService;
    @Autowired
    TransactionService transactionService;
    @Autowired
    UpdateService updateService;

    @GetMapping("customers")
    public List<CustomerDetailsResponse> getCustomers(){
        return customerService.getCustomers();
    }

    @GetMapping("customers/{id}")
    public Object getCustomerById(@PathVariable long id){
        return customerService.getCustomerById(id);
    }

    @PostMapping("customers")
    public CustomerResponse addCustomers(@RequestBody CustomerDetailedAddress cdo){

        return customerService.addCustomer(cdo);

    }
    @GetMapping("balances")
    public Object getBalance(@RequestParam(required = false) String cust_id, @RequestParam(required = false) String acc_id){

        if( cust_id == null && acc_id ==null){
            return new Exceptionresponse("invalid detalis","hct404");
        }
        else if(cust_id !=null && acc_id==null){
            return balanceService.getBalanceByCustId(Long.parseLong(cust_id));
        }
        else if(cust_id==null && acc_id!=null){
            return balanceService.getBalanceByAccId(Long.parseLong(acc_id));
        }
        else{
            return balanceService.getBalanceByAccId(Long.parseLong(acc_id));
        }

    }

    @PostMapping("transactions")
    public Object createTransaction(@RequestBody NewTransaction newTransaction){
        String type=newTransaction.getType();
        if(type.equals("credit"))
        {
            System.out.println("calling function");
            return transactionService.createTransactionCredit(newTransaction);
        }
        else
            return new Exceptionresponse("invalid type","HCT404");
    }
    @GetMapping("transactions")
    public List<AccTransaction> getTransactions(@RequestParam(required = false) String acc_id, @RequestParam(required = false) String ref_id){
        if( acc_id == null && ref_id == null ){
            return new ArrayList<>();
        }
        else if(acc_id!=null && ref_id==null){
            System.out.println("using accid");
            return transactionService.getTransactionByAccId(Long.parseLong(acc_id));
        }
        else if (acc_id==null && ref_id!=null) {

            return transactionService.getTransactionByRefId(Long.parseLong(ref_id));
        }
        else{
            AccTransaction at= transactionService.getTransactionByAccIdRefId(Long.parseLong(acc_id),Long.parseLong(ref_id));
            List<AccTransaction> li=new ArrayList<>();
            li.add(at);
            return li;
        }


    }

    @PutMapping("updatedetails/{id}")
    public String updateCustomerDetails(@PathVariable long id,@RequestBody CustomerDetailedAddress cdo){
        return updateService.updateCustomerDetails(id,cdo);
    }


}
