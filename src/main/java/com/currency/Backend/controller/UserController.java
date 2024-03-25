package com.currency.Backend.controller;

import com.currency.Backend.model.DTOs.CreateUserReq;
import com.currency.Backend.model.DTOs.RegisterResponse;
import com.currency.Backend.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/user")
@Validated
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }
    @PostMapping(path = "save")
    public ResponseEntity<RegisterResponse> save(@Valid @RequestBody CreateUserReq req){
        return ResponseEntity.ok().body(userService.save(req));
    }
}
