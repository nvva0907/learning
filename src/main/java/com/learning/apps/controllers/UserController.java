package com.learning.apps.controllers;

import com.learning.apps.dtos.UserSignUpDTO;
import com.learning.apps.responses.CustomResponse;
import com.learning.domains.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    UserService userService;

    @PostMapping("/sign-up")
    public CustomResponse<?> signUp(@Valid @RequestBody UserSignUpDTO dto) {
        return userService.signUp(dto);
    }

}
