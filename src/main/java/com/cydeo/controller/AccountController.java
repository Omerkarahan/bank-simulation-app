package com.cydeo.controller;

import com.cydeo.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /*
    localhost:8080/index
    write a method to return index.html page including account list
    endpoint: index
     */
    @GetMapping("index")
    public String getIndex(Model model){

        model.addAttribute("accountList", accountService.listAllAccount());
        return "account/index";
    }
}
