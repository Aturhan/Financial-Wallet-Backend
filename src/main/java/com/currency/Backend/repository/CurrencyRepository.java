package com.currency.Backend.repository;

import com.currency.Backend.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency,String> {
    Currency findCurrencyByCode(String code);
}
