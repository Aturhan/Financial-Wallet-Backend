package com.currency.Backend.service;

import com.currency.Backend.model.DTOs.AuthReq;
import com.currency.Backend.model.DTOs.AuthRes;
import com.currency.Backend.model.DTOs.CreateUserReq;

public interface IUserService {
   void save(CreateUserReq req);
   AuthRes login(AuthReq req);
}
