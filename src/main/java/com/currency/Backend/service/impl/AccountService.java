package com.currency.Backend.service.impl;

import com.currency.Backend.exception.EntityNotFoundException;
import com.currency.Backend.model.Account;


import com.currency.Backend.model.constants.SpendType;
import com.currency.Backend.repository.AccountRepository;
import com.currency.Backend.service.IAccountService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
    public Double getAccountAmount(String id) {
        Account account =  accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Account Not Found!"));
        return account.getAmount();
    }

    @Override
    public Account save(Double amount) {

        Account account = Account.builder()
                .amount(amount)
                .build();
         return accountRepository.save(account);


    }
    @Transactional
    @Override
    public Account updateTotalAmount(Account account, Double spendAmount, SpendType type){
        if (type.equals(SpendType.INCOME)){
        account.setAmount(account.getAmount() + spendAmount);
        }else {
            account.setAmount(account.getAmount() - spendAmount);
        }
        return accountRepository.save(account);
    }
}
