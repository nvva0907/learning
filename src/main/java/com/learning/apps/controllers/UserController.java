package com.learning.apps.controllers;

import com.learning.apps.dtos.UserSignUpDTO;
import com.learning.apps.responses.CustomResponse;
import com.learning.domains.services.UserService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/all")
    public CustomResponse<?> getAll() {
        return userService.getAll();
    }

}
