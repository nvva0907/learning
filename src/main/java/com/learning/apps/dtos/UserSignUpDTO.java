package com.learning.apps.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSignUpDTO {
    private String fullName;
    private String username;
    private String image;
    private String email;
    private String password;
    private String phoneNumber;
}
