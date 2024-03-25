package com.currency.Backend.model.DTOs;

import lombok.Builder;

@Builder
public record SpendDto(
        String title,
        String id,
        String accountId,
        Double amount
) {
}
