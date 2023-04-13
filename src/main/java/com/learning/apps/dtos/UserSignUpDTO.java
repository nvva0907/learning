package com.learning.apps.dtos;

import com.learning.domains.annotations.ValidStringType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSignUpDTO {
    @ValidStringType(message = "Full Name is required")
    private String fullName;
    @ValidStringType(message = "Username is required")
    private String username;
    private String image;
    @ValidStringType(message = "Email is required")
    private String email;
    @ValidStringType(message = "Password is required")
    private String password;
    @ValidStringType(message = "Phone number is required")
    private String phoneNumber;
}
