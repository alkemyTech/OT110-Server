package com.alkemy.ong.controller;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.security.SecurityConstant;
import com.alkemy.ong.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final IUserService  iUserService;

    @GetMapping()
    @PreAuthorize(SecurityConstant.ADMIN)
    public ResponseEntity<List<UserDto>> getUsers(){
            List<UserDto> list = iUserService.getUsers();
            return new ResponseEntity<List<UserDto>>(list, HttpStatus.OK);
    }
}

