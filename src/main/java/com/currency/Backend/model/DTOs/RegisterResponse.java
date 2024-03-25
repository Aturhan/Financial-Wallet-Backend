package com.currency.Backend.model.DTOs;

import lombok.Builder;

@Builder
public record RegisterResponse(
        String message,
        Boolean result
) {
}
