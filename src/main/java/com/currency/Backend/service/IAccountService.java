package com.currency.Backend.service;

import com.currency.Backend.model.Account;
import com.currency.Backend.model.DTOs.CreateAccountReq;


public interface IAccountService {
    Account findById(String id);
    Account save(CreateAccountReq req);
}
