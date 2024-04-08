package com.example.service;

import com.example.controller.GenerateRandom;
import com.example.model.AccBalance;
import com.example.model.AccTransaction;
import com.example.model.NewTransaction;
import com.example.model.Response.Exceptionresponse;
import com.example.model.Response.TransactionGetResponse;
import com.example.model.Response.TransactionResponse;
import com.example.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

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

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public TransactionResponse createTransactionCredit(NewTransaction newTransaction) {
        System.out.println("in creating transaction function");
        GenerateRandom gr = new GenerateRandom();
        Timestamp currentTimestamp = new Timestamp(new Date().getTime());
        long acc_id = newTransaction.getAcc_id();
        long to_acc_id = newTransaction.getTo_acc_id();
        double amount = newTransaction.getAmount();

        long tid1 = gr.getRandom();
        long tid2 = gr.getRandom();
        long rid = gr.getRandom();


        Optional<AccBalance> senderOptional = accBalanceRepo.findById(acc_id);
        if (senderOptional.isPresent()) {
            AccBalance sender = senderOptional.get();
            double senderBalance = sender.getBalance();
            if (senderBalance >= amount) {
                sender.setBalance(senderBalance - amount);
                AccTransaction at1 = new AccTransaction(tid1,rid,acc_id,0,amount,sender.getBalance(),currentTimestamp);
                transactionRepo.save(at1);
                accBalanceRepo.save(sender);
            } else {
                return new TransactionResponse("Insufficient balance in sender's account","hct400",0);
            }
        } else {
            return new TransactionResponse("Sender account not found","hct400",0);
        }

        Optional<AccBalance> receiverOptional = accBalanceRepo.findById(to_acc_id);
        if (receiverOptional.isPresent()) {
            AccBalance receiver = receiverOptional.get();
            double receiverBalance = receiver.getBalance();
            receiver.setBalance(receiverBalance + amount);
            AccTransaction at2 = new AccTransaction(tid2,rid,to_acc_id,amount,0,receiver.getBalance(),currentTimestamp);
            transactionRepo.save(at2);
            accBalanceRepo.save(receiver);
        } else {
            return new TransactionResponse("Receiver account not found","hct400",0);
        }


        return new TransactionResponse("Transaction Success","HCT200",rid);
    }


    public List<AccTransaction> getTransactionByAccId(long accid) {

        try{
            return transactionRepo.findByAccId(accid);
        }
        catch (Exception e){
            Exceptionresponse er=new Exceptionresponse("cannot found","HCT404");
            System.out.println(er);
            return null;
        }
    }

    public List<AccTransaction> getTransactionByRefId(long refId) {

        try{
            return  transactionRepo.findByRefId(refId);
        }
        catch (Exception e){
            Exceptionresponse er=new Exceptionresponse("cannot found","HCT404");
            System.out.println(er);
            return null;
        }
    }

    public AccTransaction getTransactionByAccIdRefId(long l1, long l2) {
        try{
            return transactionRepo.findByAccIdRefId(l1,l2);
        }
        catch (Exception e){
            Exceptionresponse er=new Exceptionresponse("cannot found","HCT404");
            System.out.println(er);
            return null;
        }
    }
}
