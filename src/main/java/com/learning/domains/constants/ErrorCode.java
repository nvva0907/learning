package com.learning.domains.constants;

import com.learning.domains.annotations.ErrorMessage;

public class ErrorCode {

    @ErrorMessage("Create Success")
    public static final String CREATE_SUCCESS = "CREATE_SUCCESS";

    @ErrorMessage("Username already in use!")
    public static final String USERNAME_ALREADY_USED = "USERNAME_ALREADY_USED";

    @ErrorMessage("Email already in use!")
    public static final String EMAIL_ALREADY_USED = "EMAIL_ALREADY_USED";

    @ErrorMessage("Phone number already in use!")
    public static final String PHONE_NUMBER_ALREADY_USED = "PHONE_NUMBER_ALREADY_USED";
}
