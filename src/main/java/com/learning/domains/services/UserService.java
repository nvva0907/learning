package com.learning.domains.services;

import com.learning.apps.dtos.UserSignUpDTO;
import com.learning.apps.responses.CustomResponse;

public interface UserService {
    CustomResponse<?> signUp(UserSignUpDTO dto);
}
