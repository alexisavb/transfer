package com.baufest.transfer.infrastructure.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Optional;

@Slf4j
@Component
public class JWTInterceptor implements HandlerInterceptor {
    private final static String PREFIX = "Bearer ";
    private final static String AUTHORIZATION_HEADER = "Authorization";
    private final static String KEY = "sub";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Optional.ofNullable(request.getHeader(AUTHORIZATION_HEADER)).ifPresent(
                value -> {
                    try {
                        DecodedJWT jwt = JWT.decode(value.split(PREFIX)[1]);
                        String userId = null != jwt.getClaim(KEY).asString() ? jwt.getClaim(KEY).asString() : "";
                        request.setAttribute("user_id", userId);
                    } catch (Exception e1) {
                        log.error("Could not process the JWT provided...", e1);
                        throw new InvalidClaimException("Invalid token");
                    }
                });
        return true;
    }

}
