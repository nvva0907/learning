package com.learning.apps.controllers;

import com.learning.apps.dtos.LoginRequestDTO;
import com.learning.apps.responses.CustomResponse;
import com.learning.domains.security_config.CustomUserDetails;
import com.learning.domains.security_config.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {
    @Resource
    AuthenticationManager authenticationManager;

    @Resource
    JwtTokenProvider tokenProvider;

    @PostMapping(value = "/login")
    public CustomResponse<?> login(@Valid @RequestBody LoginRequestDTO dto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        return CustomResponse.ok(jwt);
    }
}
