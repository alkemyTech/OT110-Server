package com.alkemy.ong.security;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LoginDTO {

    @NotBlank
    @NotNull(message= "You must enter your email to be able to login")
    private String email;
    @NotBlank
    @NotNull(message= "You must enter your password to be able to login")
    private String password;
    
    public LoginDTO() {
    	
    }
    
    

    public LoginDTO(@NotBlank @NotNull(message = "You must enter your email to be able to login") String email,
			@NotBlank @NotNull(message = "You must enter your password to be able to login") String password) {
		super();
		this.email = email;
		this.password = password;
	}



	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
