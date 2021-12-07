package com.alkemy.ong.security.service;

import com.alkemy.ong.security.dto.LoggedUserDto;
import com.alkemy.ong.security.dto.LoginDto;

public interface IAuthenticationService {
	LoggedUserDto signInAndReturnJWT(LoginDto signInRequest);
}
