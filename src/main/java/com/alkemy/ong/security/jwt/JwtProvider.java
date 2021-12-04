package com.alkemy.ong.security.jwt;

import org.springframework.security.core.Authentication;

import com.alkemy.ong.security.UserDetailsImpl;

import javax.servlet.http.HttpServletRequest;

public interface JwtProvider {
    String generateToken(UserDetailsImpl auth);

    Authentication getAuthentication(HttpServletRequest request);

    boolean validateToken(HttpServletRequest request);
}
