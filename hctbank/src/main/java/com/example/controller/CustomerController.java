package com.example.controller;

import com.example.model.*;
import com.example.model.Response.*;
import com.example.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    DeleteService deleteService;
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

    @PostMapping("customers")
    public Object addCustomer(@RequestBody CustomerDetailedAddress cdo){

        return customerService.addCustomer(cdo);
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
    public Object getTransactions(@RequestParam(required = false) String acc_id, @RequestParam(required = false) String ref_id){
        if( acc_id == null && ref_id == null ){
            return new Exceptionresponse("invalid details","Hct404");
        }
        else if(acc_id!=null && ref_id==null){
            System.out.println("using accid");
            return transactionService.getTransactionByAccId(Long.parseLong(acc_id));
        }
        else if (acc_id==null && ref_id!=null) {

            return transactionService.getTransactionByRefId(Long.parseLong(ref_id));
        }
        else
            return transactionService.getTransactionByAccIdRefId(Long.parseLong(acc_id),Long.parseLong(ref_id));

    }
    @DeleteMapping("delete/{cust_id}")
    public String deleteCustomer(@PathVariable long cust_id){
        return deleteService.deleteCustomer(cust_id);

    }
    @PutMapping("updatedetails/{id}")
    public String updateCustomerDetails(@PathVariable long id,@RequestBody CustomerDetailedAddress cdo){
        return updateService.updateCustomerDetails(id,cdo);
    }


}
