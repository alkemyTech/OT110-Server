package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.UserUpdateDto;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.security.dto.LoggedUserDto;
import com.alkemy.ong.security.dto.LoginDto;
import com.alkemy.ong.security.service.IAuthenticationService;
import com.alkemy.ong.service.IEmailService;
import com.alkemy.ong.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.dto.UserRequest;
import com.alkemy.ong.exception.EmailExistException;
import com.alkemy.ong.model.User;
import com.alkemy.ong.repository.RoleRepository;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.security.RoleEnum;


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

    private MessageSource messageSource;

    @Override
    public LoggedUserDto createUser(UserRequest userRequest) throws EmailExistException {
        if (userRepository.findByEmail(userRequest.getEmail()).isPresent()) {
            throw new EmailExistException(userRequest.getEmail());
        }
        emailService.sendWelcomeEmail(userRequest.getEmail(), userRequest.getFirstName(), userRequest.getLastName());
        LoginDto login = new LoginDto ();
        login.setEmail(userRequest.getEmail());
        login.setPassword(userRequest.getPassword());
        userRepository.save(generateUser(userRequest));
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
    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

	@Override
	public void makeAdmin(String username) {
		// TODO Auto-generated method stub
		
	}

    @Override
    public List<UserDto> getUsers() {

        List<UserDto> userDto = userRepository.findAll().stream()
                .map(this::mapUserToUserDto)
                .collect(Collectors.toList());
        return userDto;
    }

    @Override
    public UserUpdateDto update(Long id, UserUpdateDto userUpdateDto) {
        return null;
    }

    private UserDto mapUserToUserDto(User user){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(user, UserDto.class);
    }    
    
//    @Override
//    @Transactional
//    public void makeAdmin(String username){
//        userRepository.updateUserRole(username, roleRepository.findByName(RoleEnum.ADMIN.getName()));
//    }

    @Override
    public ResponseEntity<?> deleteById(Long id) {

        String notFoundUserMessage = messageSource.getMessage("user.notFound", null, Locale.US);
        String isDeletedCategoryMessage = messageSource.getMessage("user.isDeleted", null, Locale.US);

        User user = userRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(notFoundUserMessage));
        userRepository.delete(user);
        return new ResponseEntity<>(isDeletedCategoryMessage, HttpStatus.OK);

    }


}
