package com.alkemy.ong.security.controller;

import javax.validation.Valid;

import com.alkemy.ong.security.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alkemy.ong.dto.UserRequest;
import com.alkemy.ong.exception.EmailExistException;
import com.alkemy.ong.security.dto.LoginDto;
import com.alkemy.ong.security.service.IAuthenticationService;
import com.alkemy.ong.service.IUserService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private IAuthenticationService authenticationService;

    @Autowired
    private IUserService userService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @PostMapping("/register")
    public ResponseEntity<?> signUp(@Valid @RequestBody UserRequest user) throws EmailExistException{
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> signIn(@Valid @RequestBody LoginDto user){
        return new ResponseEntity<>(authenticationService.signInAndReturnJWT(user), HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getAuthenticatedUserDetails(@RequestHeader(value = "Authorization") String authorizationHeader){
        return new ResponseEntity<>(customUserDetailsService.getUserDetails(authorizationHeader),HttpStatus.OK);
    }

}
