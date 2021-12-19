package com.alkemy.ong.service;


import java.util.List;
import java.util.Optional;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.dto.UserRequest;
import com.alkemy.ong.dto.UserUpdateDto;
import com.alkemy.ong.model.User;
import com.alkemy.ong.security.dto.LoggedUserDto;
import com.alkemy.ong.exception.EmailExistException;
import org.springframework.http.ResponseEntity;

public interface IUserService {
    String makeAdmin(String email);

    Optional<User> findByEmail(String email);

    LoggedUserDto createUser(UserRequest userRequest) throws EmailExistException;

    List<UserDto> getUsers();

    UserUpdateDto update(Long id, UserUpdateDto userUpdateDto);

    ResponseEntity<?> deleteById(Long id);

    User findById(Long id);
}
