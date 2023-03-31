package com.learning.domains.security_config;

import com.learning.domains.constants.Constant;
import com.learning.domains.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Resource
    private JwtTokenProvider jwtTokenProvider;

    @Resource
    UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            // get jwt from request
            String jwt = getJWTFromClient(request);
            if (StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt)) {
                // get user id from jwt
                String username = jwtTokenProvider.getUsernameFromJWT(jwt);
                // get user information from id
                UserDetails userDetails = userService.loadUserByUsername(username);
                if (!ObjectUtils.isEmpty(userDetails)) {
                    // set user into security context
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (Exception ex) {
            log.error("failed to set user info to security context");
        }
        filterChain.doFilter(request, response);
    }

    private String getJWTFromClient(HttpServletRequest request) {
        String bearerToken = request.getHeader(Constant.AUTHORIZATION);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(Constant.BEARER)) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
