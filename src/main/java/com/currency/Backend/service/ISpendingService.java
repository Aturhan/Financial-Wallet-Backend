package com.currency.Backend.service;

import com.currency.Backend.model.DTOs.CreateAccountReq;
import com.currency.Backend.model.DTOs.CreateSpendRes;
import com.currency.Backend.model.DTOs.CreateSpendingReq;
import com.currency.Backend.model.DTOs.SpendDto;

import java.util.List;

public interface ISpendingService {
    CreateSpendRes save(CreateSpendingReq req);
    List<SpendDto> getAll(String accountId);
    SpendDto findById(String id);
    void delete(String id);
}
