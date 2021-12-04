package com.alkemy.ong.security;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginDto {
	
    @NotBlank
    @NotNull(message= "You must enter your email to be able to login")
    private String email;
    @NotBlank
    @NotNull(message= "You must enter your password to be able to login")
    private String password;
    
}
