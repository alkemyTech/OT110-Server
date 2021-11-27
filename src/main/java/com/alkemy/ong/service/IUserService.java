package com.alkemy.ong.service;

import java.util.Optional;

import com.alkemy.ong.model.User;

public interface IUserService {

	Optional<User> findByEmail(String email);

}
