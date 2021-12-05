package com.alkemy.ong.security.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.alkemy.ong.model.User;
import com.alkemy.ong.security.dto.LoggedUserDto;
import com.alkemy.ong.security.dto.LoginDto;
import com.alkemy.ong.security.jwt.IJwtProvider;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IJwtProvider jwtProvider;

    @Override
    public LoggedUserDto signInAndReturnJWT(LoginDto signInRequest){
    	log.info("[AuthenticationServiceImpl] -> signInAndReturnJWT ");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword())
        );
        log.info("[AuthenticationServiceImpl] -> Cast (UserDetailsImpl) authentication.getPrincipal()");
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        log.info("[AuthenticationServiceImpl] -> jwtProvider.generateToken() ");
        String jwt = jwtProvider.generateToken(userDetails);
        User signInUser = userDetails.getUser();
        signInUser.setToken(jwt);
        log.info("[AuthenticationServiceImpl] -> Mapping User to LoggedUserDto Data");
        LoggedUserDto loggedUser = new LoggedUserDto();
        loggedUser.setEmail(signInUser.getEmail());
        loggedUser.setToken(signInUser.getToken());
        log.info("[AuthenticationServiceImpl] -> Return loggedUser " + loggedUser.toString());
        return loggedUser;
    }

}
