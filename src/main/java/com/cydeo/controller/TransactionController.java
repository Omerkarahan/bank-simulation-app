package com.cydeo.controller;

import com.cydeo.model.Account;
import com.cydeo.model.Transaction;
import com.cydeo.service.AccountService;
import com.cydeo.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@AllArgsConstructor
@Controller
public class TransactionController {

    private  final AccountService accountService;
    private final TransactionService transactionService;


    @GetMapping("/make-transfer")
    public String makeTransfer(Model model){

        //we need all accounts to provide  them as sender, receiver
        model.addAttribute("accounts",accountService.listAllAccount());
        //we need empty transaction object to get info from UI
        model.addAttribute("transaction", Transaction.builder().build());
        //we need list of last 10 transaction
        model.addAttribute("lastTransactions", transactionService.lastTransactionsList());

        return "transaction/make-transfer";
    }

    //write a post method, that takes transaction object from the method above
    //complete the make transfer and return the same page

    @PostMapping("/transfer")
    public String postMakeTransfer(@ModelAttribute("transaction") Transaction transaction,Model model){

        //I have UUID, but I need to provide Account to make transfer method.
        Account sender= accountService.retrieveById(transaction.getSender());
        Account receiver= accountService.retrieveById(transaction.getReceiver());

        transactionService.makeTransfer(sender,receiver,transaction.getAmount(),new Date(),transaction.getMessage());

        return "redirect:/make-transfer";
    }
    }

