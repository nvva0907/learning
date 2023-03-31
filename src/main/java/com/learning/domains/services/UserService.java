package com.learning.domains.services;

import com.learning.apps.dtos.UserSignUpDTO;
import com.learning.apps.responses.CustomResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    CustomResponse<?> signUp(UserSignUpDTO dto);

    CustomResponse<?> getAll();
}
