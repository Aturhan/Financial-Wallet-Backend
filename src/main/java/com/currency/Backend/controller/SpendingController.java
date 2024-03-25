package com.currency.Backend.controller;

import com.currency.Backend.model.DTOs.CreateSpendRes;
import com.currency.Backend.model.DTOs.CreateSpendingReq;
import com.currency.Backend.model.DTOs.SpendDto;
import com.currency.Backend.service.ISpendingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/spends")
@Validated
public class SpendingController {
    private final ISpendingService spendingService;

    public SpendingController(ISpendingService spendingService) {
        this.spendingService = spendingService;
    }
    @PostMapping("save")
    public ResponseEntity<CreateSpendRes> save(@Valid @RequestBody CreateSpendingReq req){
        return ResponseEntity.ok().body(spendingService.save(req));
    }
    @GetMapping(path = "{id}")
    public ResponseEntity<SpendDto> getById(@PathVariable("id") String id){
        return ResponseEntity.ok().body(spendingService.findById(id));
    }

    @GetMapping(path = "all/{accountId}")
    public ResponseEntity<List<SpendDto>> getAllByAccountId(@PathVariable("accountId") String accountId){
        return ResponseEntity.ok().body(spendingService.getAll(accountId));
    }
    @DeleteMapping("delete/{id}")
    public void deleteById(@PathVariable("id")String id){
        spendingService.delete(id);
    }
}
