package com.currency.Backend.controller;


import com.currency.Backend.model.Account;
import com.currency.Backend.service.IAccountService;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/account")
@Validated
public class AccountController {
    private final IAccountService accountService;

    public AccountController(IAccountService accountService) {
        this.accountService = accountService;
    }
    @GetMapping(path = "{accountId}")
    public ResponseEntity<Double> getAccountById(@PathVariable("accountId") String accountId){
        return ResponseEntity.ok().body(accountService.getAccountAmount(accountId));
    }

}
