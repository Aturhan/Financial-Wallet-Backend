package com.currency.Backend.repository;

import com.currency.Backend.model.Spending;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpendingRepository extends JpaRepository<Spending,String> {
   List<Spending> findAllByAccountId(String accountId);
}
