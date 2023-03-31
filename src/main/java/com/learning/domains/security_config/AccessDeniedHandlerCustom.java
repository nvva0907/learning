package com.learning.domains.security_config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.learning.apps.responses.CustomResponse;
import com.learning.domains.constants.ErrorCode;
import com.learning.domains.utils.ErrorCodeUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class AccessDeniedHandlerCustom implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse httpServletResponse, AuthenticationException authException) throws IOException, ServletException {
        CustomResponse<?> response = new CustomResponse<>(null, Collections.singletonList(ErrorCodeUtils.getErrorMessage(ErrorCode.UNAUTHORIZED)), String.valueOf(HttpServletResponse.SC_UNAUTHORIZED));
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(response);
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpServletResponse.getOutputStream().println(json);
    }
}
