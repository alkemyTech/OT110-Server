package com.alkemy.ong.security.service;

import com.alkemy.ong.model.User;
import com.alkemy.ong.security.dto.LoggedUserDto;
import com.alkemy.ong.security.dto.LoginDto;
import com.alkemy.ong.security.jwt.IJwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final IJwtProvider jwtProvider;

    @Override
    public LoggedUserDto signInAndReturnJWT(LoginDto signInRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword())
        );
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = jwtProvider.generateToken(userDetails);
        User signInUser = userDetails.getUser();
        signInUser.setToken(jwt);
        LoggedUserDto loggedUser = new LoggedUserDto();
        loggedUser.setToken(signInUser.getToken());
        return loggedUser;
    }

}
