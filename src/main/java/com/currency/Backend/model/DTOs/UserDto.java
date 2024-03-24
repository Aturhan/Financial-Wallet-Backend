package com.currency.Backend.model.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Builder
public class UserDto {
    private String name;
    private String email;
    private Set<String> currencies;
}
