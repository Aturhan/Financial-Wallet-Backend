package com.currency.Backend.service.impl;

import com.currency.Backend.exception.EntityNotFoundException;
import com.currency.Backend.model.Account;
import com.currency.Backend.model.DTOs.CreateAccountReq;

import com.currency.Backend.repository.AccountRepository;
import com.currency.Backend.service.IAccountService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class AccountService implements IAccountService {
    private final AccountRepository accountRepository;


    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;

    }

    @Override
    public Account findById(String id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Account Not Found!"));
    }

    @Override
    public Account save(CreateAccountReq req) {

        Account account = Account.builder()
                .amount(req.amount())
                .build();
         return accountRepository.save(account);


    }
}
