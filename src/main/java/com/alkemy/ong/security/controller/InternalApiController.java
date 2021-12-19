package com.alkemy.ong.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alkemy.ong.security.SecurityConstant;
import com.alkemy.ong.service.IUserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/internal")
@RequiredArgsConstructor
public class InternalApiController {

	private final IUserService userService;
	
    @PutMapping("/admin/{email}")
    @PreAuthorize(SecurityConstant.SYSTEM_MANAGER)
    public ResponseEntity<?> makeAdmin(@PathVariable String email){
        String message = userService.makeAdmin(email);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }	
	
}
