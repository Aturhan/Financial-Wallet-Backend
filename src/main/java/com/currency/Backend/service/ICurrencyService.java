package com.currency.Backend.service;

import com.currency.Backend.model.Currency;

import java.util.List;

public interface ICurrencyService {
    Currency findOne(String code);
    List<Currency> getAll();
    void scrapeCurrencyData();
}
