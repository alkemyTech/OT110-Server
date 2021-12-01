package com.alkemy.ong.service;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    public List<UserDto> getUsers();

    Optional<User> findByEmail(String email);
}


