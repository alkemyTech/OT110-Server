package com.alkemy.ong.service;


import java.util.List;
import java.util.Optional;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.dto.UserRequest;
import com.alkemy.ong.model.User;
import com.alkemy.ong.exception.EmailExistException;

public interface IUserService {

    void makeAdmin(String username);

	Optional<User> findByEmail(String email);

	User createUser(UserRequest userRequest) throws EmailExistException;

	List<UserDto> getUsers();
}
