package com.currency.Backend.controller;

import com.currency.Backend.model.DTOs.AuthReq;
import com.currency.Backend.model.DTOs.AuthRes;
import com.currency.Backend.service.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/auth")
public class AuthController {
    private final IUserService userService;

    public AuthController(IUserService userService) {
        this.userService = userService;
    }
    @PostMapping(path = "login")
    public ResponseEntity<AuthRes> login(@RequestBody  AuthReq req){
        return ResponseEntity.ok().body(userService.login(req));
    }
}
