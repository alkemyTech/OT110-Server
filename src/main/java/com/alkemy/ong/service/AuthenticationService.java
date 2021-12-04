package com.alkemy.ong.service;

import com.alkemy.ong.security.LoggedUserDto;
import com.alkemy.ong.security.LoginDto;

public interface AuthenticationService {
	LoggedUserDto signInAndReturnJWT(LoginDto signInRequest);
}
