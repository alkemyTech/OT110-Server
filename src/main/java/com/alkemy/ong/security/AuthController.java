package com.alkemy.ong.security;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginDTO loginDTO, BindingResult bindingResult) {
		
		try {
			if (bindingResult.hasErrors())
				return new ResponseEntity<JwtDTO>(HttpStatus.BAD_REQUEST);

			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = JwtProviderImpl.generateToken(authentication);
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			JwtDTO jwtDto = new JwtDTO(jwt, userDetails.getUsername(), userDetails.getAuthorities());

			return new ResponseEntity<JwtDTO>(jwtDto, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>("{ok = false }"+ "error: " + e.getLocalizedMessage(), HttpStatus.CONFLICT);
		}
		
	}
	
	
}
