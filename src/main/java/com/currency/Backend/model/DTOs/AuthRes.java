package com.currency.Backend.model.DTOs;

import lombok.Builder;

@Builder
public record AuthRes(
        String message,
        Boolean result,
        UserDto userDto,
        String token
) {
}
