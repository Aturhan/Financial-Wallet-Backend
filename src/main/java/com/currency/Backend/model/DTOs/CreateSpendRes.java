package com.currency.Backend.model.DTOs;

import com.currency.Backend.model.Spending;
import lombok.Builder;

@Builder
public record CreateSpendRes(
        Boolean result,
        String message,
        SpendDto spendDto){
}
