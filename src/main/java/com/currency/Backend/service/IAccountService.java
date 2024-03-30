package com.currency.Backend.service;

import com.currency.Backend.model.Account;
import com.currency.Backend.model.constants.SpendType;


public interface IAccountService {
    Account findById(String id);
    Double getAccountAmount(String id);
    Account save(Double amount);
    Account updateTotalAmount(Account account, Double spendAmount, SpendType type);
}
