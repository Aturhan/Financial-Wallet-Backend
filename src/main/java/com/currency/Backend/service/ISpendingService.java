package com.currency.Backend.service;


import com.currency.Backend.model.DTOs.CreateSpendRes;
import com.currency.Backend.model.DTOs.CreateSpendingReq;
import com.currency.Backend.model.DTOs.CustomResponse;
import com.currency.Backend.model.DTOs.SpendDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ISpendingService {
    CreateSpendRes save(CreateSpendingReq req);
    Page<SpendDto> getAll(Pageable pageable, String accountId);
    SpendDto findById(String id);
    CustomResponse delete(String id);
}
