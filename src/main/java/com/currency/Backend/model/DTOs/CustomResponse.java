package com.currency.Backend.model.DTOs;

import lombok.Builder;

@Builder
public record CustomResponse(
        String message,
        Boolean result
) {
}
