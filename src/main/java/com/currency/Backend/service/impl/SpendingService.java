package com.currency.Backend.service.impl;

import com.currency.Backend.exception.EntityNotFoundException;
import com.currency.Backend.model.Account;

import com.currency.Backend.model.DTOs.CreateSpendRes;
import com.currency.Backend.model.DTOs.CreateSpendingReq;
import com.currency.Backend.model.DTOs.CustomResponse;
import com.currency.Backend.model.DTOs.SpendDto;
import com.currency.Backend.model.Spending;
import com.currency.Backend.repository.SpendingRepository;
import com.currency.Backend.service.IAccountService;
import com.currency.Backend.service.ISpendingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SpendingService implements ISpendingService {
    private final SpendingRepository spendingRepository;
    private final IAccountService accountService;

    public SpendingService(SpendingRepository spendingRepository, IAccountService accountService) {
        this.spendingRepository = spendingRepository;
        this.accountService = accountService;
    }
    @Transactional
    @Override
    public CreateSpendRes save(CreateSpendingReq req) {
        Account account = accountService.findById(req.accountId());
        Spending spending = Spending.builder()
                .title(req.title())
                .account(account)
                .spendType(req.spendType())
                .amount(req.amount())
                .build();
        Spending saved = spendingRepository.save(spending);
        accountService.updateTotalAmount(account, req.amount(), req.spendType());
        return CreateSpendRes.builder()
                .spendDto(SpendDto.builder()
                        .id(saved.getId())
                        .spendType(saved.getSpendType().getValue())
                        .amount(saved.getAmount())
                        .accountId(saved.getAccount().getId())
                        .title(saved.getTitle())
                        .build())
                .result(true)
                .message("Spending saved")
                .build();
    }

    @Override
    public Page<SpendDto> getAll(Pageable pageable, String accountId) {
        Page<Spending>  spends = spendingRepository.findAllByAccountId(pageable,accountId);

               return spends.map(spend -> SpendDto.builder()
                        .title(spend.getTitle())
                        .spendType(spend.getSpendType().getValue())
                        .accountId(spend.getAccount().getId())
                        .id(spend.getId())
                        .amount(spend.getAmount()).build());

    }

    @Override
    public SpendDto findById(String id) {
        Spending spending =  spendingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Spend Not Found!"));
       return SpendDto.builder()
                .amount(spending.getAmount())
                .id(spending.getId())
                .title(spending.getTitle())
               .spendType(spending.getSpendType().getValue())
                .accountId(spending.getAccount().getId())
                .build();
    }

    @Override
    public CustomResponse delete(String id) {
        Spending spending = spendingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Spend Not found"));
        spendingRepository.delete(spending);
        return CustomResponse.builder()
                .message("Spend deleted!")
                .result(true)
                .build();
    }
}
