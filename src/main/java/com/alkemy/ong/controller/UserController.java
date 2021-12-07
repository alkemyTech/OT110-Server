package com.alkemy.ong.controller;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.security.SecurityConstant;
import com.alkemy.ong.service.IUserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private IUserService  userService;

    @GetMapping("/users")
    @PreAuthorize(SecurityConstant.ADMIN)
    public ResponseEntity<List<UserDto>> getUsers(){
            List<UserDto> list = userService.getUsers();
            return new ResponseEntity<List<UserDto>>(list, HttpStatus.OK);
    }

}
