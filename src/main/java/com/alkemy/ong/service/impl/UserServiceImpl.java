package com.alkemy.ong.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.dto.UserRequest;
import com.alkemy.ong.dto.UserUpdateDto;
import com.alkemy.ong.exception.EmailExistException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.User;
import com.alkemy.ong.repository.RoleRepository;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.security.RoleEnum;
import com.alkemy.ong.service.IEmailService;
import com.alkemy.ong.security.dto.LoggedUserDto;
import com.alkemy.ong.security.dto.LoginDto;
import com.alkemy.ong.security.service.IAuthenticationService;
import com.alkemy.ong.service.IUserService;

import org.springframework.context.MessageSource;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IAuthenticationService authenticationService;

    @Autowired
    private IEmailService emailService;

    @Autowired
    private MessageSource messageSource;


    @Override
    public LoggedUserDto createUser(UserRequest userRequest) throws EmailExistException {
        if (userRepository.findByEmail(userRequest.getEmail()).isPresent()) {
            throw new EmailExistException(userRequest.getEmail());
        }

        userRepository.save(generateUser(userRequest));
        emailService.sendWelcomeEmail(userRequest.getEmail(), userRequest.getFirstName(), userRequest.getLastName());
        LoginDto login = new LoginDto();
        login.setEmail(userRequest.getEmail());
        login.setPassword(userRequest.getPassword());
        return authenticationService.signInAndReturnJWT(login);
    }


    private User generateUser(UserRequest userRequest) {
        User user = new User();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPhoto(userRequest.getPhoto());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setRole(roleRepository.findByName(RoleEnum.USER.getName()));
        user.setDateCreation(LocalDateTime.now());
        return user;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void makeAdmin(String username) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<UserDto> getUsers() {

        String userListIsEmpty = messageSource.getMessage("user.listEmpty", null, Locale.US);

        List<UserDto> userDto = userRepository.findAll().stream()
                .map(this::mapUserToUserDto)
                .collect(Collectors.toList());
        if(userDto.isEmpty()){
            throw new NotFoundException(userListIsEmpty);
        }
        return userDto;
    }

    private UserDto mapUserToUserDto(User user) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(user, UserDto.class);
    }

    private UserUpdateDto mapUserToUserUpdateDto(User user) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(user, UserUpdateDto.class);
    }

    public UserUpdateDto update(Long id, UserUpdateDto userUpdateDto) {
        Optional<User> entity = userRepository.findById(id);
        String messageError = messageSource.getMessage("updateUserMessageTemplate.failure", null, Locale.US);
        if (!entity.isPresent()) {
            throw new NotFoundException(messageError);
        }
        userRefreshValues(entity.get(), userUpdateDto);
        User userSaved = userRepository.save(entity.get());
        UserUpdateDto result = mapUserToUserUpdateDto(userSaved);
        return result;

    }

    public void userRefreshValues(User user, UserUpdateDto userUpdateDto) {
        if (userUpdateDto.getFirstName() != null) {
            user.setFirstName(userUpdateDto.getFirstName());
        }
        if (userUpdateDto.getLastName() != null) {
            user.setLastName(userUpdateDto.getLastName());
        }
        if (userUpdateDto.getEmail() != null) {
            user.setEmail(userUpdateDto.getEmail());
        }
        if (userUpdateDto.getPhoto() != null) {
            user.setPhoto(userUpdateDto.getPhoto());
        }
        if (userUpdateDto.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userUpdateDto.getPassword()));
        }
    }

}


