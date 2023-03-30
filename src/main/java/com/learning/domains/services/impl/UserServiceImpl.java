package com.learning.domains.services.impl;

import com.learning.apps.dtos.UserSignUpDTO;
import com.learning.apps.exceptions.BadRequestException;
import com.learning.apps.responses.CustomResponse;
import com.learning.domains.constants.Constant;
import com.learning.domains.constants.ErrorCode;
import com.learning.domains.entities.User;
import com.learning.domains.repositories.UserRepository;
import com.learning.domains.services.UserService;
import com.learning.domains.utils.ErrorCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    UserRepository userRepository;

    @Override
    public CustomResponse<?> signUp(UserSignUpDTO dto) {
        checkExistUser(dto);
        User newUser = new User();
        newUser.setFullName(dto.getFullName());
        newUser.setUsername(dto.getUsername());
        newUser.setEmail(dto.getEmail());
        newUser.setPassword(dto.getPassword());
        newUser.setPhoneNumber(dto.getPhoneNumber());
        newUser.setImage(dto.getImage());
        newUser.setRoles(Constant.USER_ROLE.USER);
        newUser.setStatus(Constant.USER_STATUS.IN_ACTIVE);
        newUser.setCreatedDate(new Timestamp(new Date().getTime()));
        newUser.setCreatedBy(dto.getUsername());
        // send mail to active account
        userRepository.save(newUser);
        return CustomResponse.ok(null);
    }

    private void checkExistUser(UserSignUpDTO dto) {
        User userExistByUsername = userRepository.findByUsername(dto.getUsername());
        if (!ObjectUtils.isEmpty(userExistByUsername)) {
            throw new BadRequestException(ErrorCodeUtils.getErrorMessage(ErrorCode.USERNAME_ALREADY_USED));
        }
        User userExistByEmail = userRepository.findByEmail(dto.getEmail());
        if (!ObjectUtils.isEmpty(userExistByEmail)) {
            throw new BadRequestException(ErrorCodeUtils.getErrorMessage(ErrorCode.EMAIL_ALREADY_USED));
        }
        User userExistByPhoneNumber = userRepository.findByPhoneNumber(dto.getPhoneNumber());
        if (!ObjectUtils.isEmpty(userExistByPhoneNumber)) {
            throw new BadRequestException(ErrorCodeUtils.getErrorMessage(ErrorCode.PHONE_NUMBER_ALREADY_USED));
        }
    }
}
