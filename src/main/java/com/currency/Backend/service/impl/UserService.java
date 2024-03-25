package com.currency.Backend.service.impl;

import com.currency.Backend.config.AuthProvider;
import com.currency.Backend.exception.EmailExistsException;
import com.currency.Backend.exception.EntityNotFoundException;
import com.currency.Backend.model.Account;
import com.currency.Backend.model.DTOs.*;
import com.currency.Backend.model.User;
import com.currency.Backend.repository.UserRepository;
import com.currency.Backend.service.IAccountService;
import com.currency.Backend.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthProvider authProvider;
    private final IAccountService accountService;


    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, AuthProvider authProvider, IAccountService accountService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authProvider = authProvider;
        this.accountService = accountService;

    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found!"));
    }

    @Transactional
    @Override
    public RegisterResponse save(CreateUserReq req) {
                User checkUser = userRepository.findByEmail(req.email());
                if (checkUser != null){
                    throw new EmailExistsException("Email is already exists");
                }
              final Account account = saveAccount(req.createAccountReq());
                User saveUser = User.builder()
                        .email(req.email())
                        .name(req.name())
                        .password(passwordEncoder.encode(req.password()))
                        .account(account)
                        .build();

                   userRepository.save(saveUser);
                  log.info("User saved: " + saveUser);

                        return RegisterResponse.builder()
                                .message("Register succeed")
                                .result(true)
                                .build();

    }

    public Account saveAccount(CreateAccountReq req){
       return accountService.save(req);
    }



    @Override
    public AuthRes login(AuthReq req) {
            User user = userRepository.findByEmail(req.email());
            if (user == null){
                return AuthRes.builder()
                        .message("User not found")
                        .result(false)
                        .token(null)
                        .userDto(null)
                        .build();
            }

        UserDto dto = UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .accountId(user.getAccount().getId())
                .amount(user.getAccount().getAmount())
                .build();
            log.info("logged user: "+dto.toString());
        return AuthRes.builder()
                .result(true)
                .message("Login succeed")
                .userDto(dto)
                .token(authProvider.createToken(dto))
                .build();
    }


}
