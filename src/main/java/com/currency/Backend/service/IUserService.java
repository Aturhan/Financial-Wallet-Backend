package com.currency.Backend.service;

import com.currency.Backend.model.DTOs.AuthReq;
import com.currency.Backend.model.DTOs.AuthRes;
import com.currency.Backend.model.DTOs.CreateUserReq;
import com.currency.Backend.model.DTOs.RegisterResponse;
import com.currency.Backend.model.User;

public interface IUserService {

   User findById(String id);
   RegisterResponse save(CreateUserReq req);
   AuthRes login(AuthReq req);
}
