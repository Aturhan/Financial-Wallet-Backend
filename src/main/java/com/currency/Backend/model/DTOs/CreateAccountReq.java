package com.currency.Backend.model.DTOs;

import com.currency.Backend.model.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CreateAccountReq(
        Double amount

) {
}
