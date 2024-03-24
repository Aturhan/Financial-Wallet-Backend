package com.currency.Backend.service;

import com.currency.Backend.config.AuthProvider;
import com.currency.Backend.exception.EmailExistsException;
import com.currency.Backend.model.DTOs.AuthReq;
import com.currency.Backend.model.DTOs.AuthRes;
import com.currency.Backend.model.DTOs.CreateUserReq;
import com.currency.Backend.model.DTOs.UserDto;
import com.currency.Backend.model.User;
import com.currency.Backend.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserService implements IUserService{
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthProvider authProvider;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, AuthProvider authProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authProvider = authProvider;
    }
    @Transactional
    @Override
    public void save(CreateUserReq req) {
        User user = userRepository.findByEmail(req.email());
        if (user != null) {
            throw new EmailExistsException("Email is already exists");
        }
            User saveUser = User.builder()
                    .email(req.email())
                    .name(req.name())
                    .currencyCodes(req.currencyCodes())
                    .password(passwordEncoder.encode(req.password()))
                    .build();
            userRepository.save(saveUser);
            log.info("User saved: " + saveUser);

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
                .name(user.getName())
                .email(user.getEmail())
                .currencies(user.getCurrencyCodes())
                .build();
        return AuthRes.builder()
                .result(true)
                .message("Login succeed")
                .userDto(dto)
                .token(authProvider.createToken(dto))
                .build();
    }


}
