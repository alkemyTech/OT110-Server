package com.alkemy.ong.controller;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.security.SecurityConstant;
import com.alkemy.ong.service.IUserService;

import java.util.List;

import com.alkemy.ong.dto.UserUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

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


    @PatchMapping("/users/{id}")
    public ResponseEntity<UserUpdateDto> updateUser (@Valid @PathVariable Long id, @RequestBody UserUpdateDto userUpdateDto) {
        UserUpdateDto result = userService.update(id, userUpdateDto);
        return new ResponseEntity<UserUpdateDto>(result, HttpStatus.OK);
    }

}
