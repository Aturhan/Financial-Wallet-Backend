package com.currency.Backend.model.DTOs;

import lombok.Builder;

@Builder
public record AuthReq(
        String email,
        String password
) {
}
