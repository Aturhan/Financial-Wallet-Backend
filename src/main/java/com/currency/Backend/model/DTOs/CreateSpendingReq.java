package com.currency.Backend.model.DTOs;

import com.currency.Backend.model.constants.SpendType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CreateSpendingReq(
        @NotNull
        @NotEmpty
        String accountId,
        @NotNull
        @NotEmpty
        String title,
        SpendType spendType,
        Double amount
) {
}
