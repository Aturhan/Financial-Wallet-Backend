package com.currency.Backend.controller;

import com.currency.Backend.model.Currency;
import com.currency.Backend.service.ICurrencyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping(path = "api/curr")
public class CurrencyController {
    private final ICurrencyService currencyService;


    public CurrencyController(ICurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("code{code}")
    public ResponseEntity<Currency> findOne(@RequestParam("code") String code){
        return ResponseEntity.ok().body(currencyService.findOne(code));
    }

    @GetMapping(path = "all")
    public ResponseEntity<List<Currency>> getAll(){
        return ResponseEntity.ok().body(currencyService.getAll());
    }
}
