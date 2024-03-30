package com.currency.Backend.repository;

import com.currency.Backend.model.Spending;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpendingRepository extends JpaRepository<Spending,String> {
   Page<Spending> findAllByAccountId(Pageable pageable ,String accountId);
}
