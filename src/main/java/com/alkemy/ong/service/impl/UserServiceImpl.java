package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.dto.UserRequest;
import com.alkemy.ong.exception.EmailExistException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.User;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.security.RoleEnum;
import com.alkemy.ong.service.IUserService;
import com.alkemy.ong.service.RoleService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService, UserDetailsService {
	
	@Autowired
    private UserRepository userRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private RoleService roleService;

    private final MessageSource messageSource;


    @Transactional
    public User createUser(UserRequest userRequest) throws EmailExistException {
        if (userRepository.findByEmail(userRequest.getEmail()) != null) {
            throw new EmailExistException(userRequest.getEmail());
        }
        return userRepository.save(generateUser(userRequest));
    }

    private User generateUser(UserRequest userRequest) {
        User user = new User();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPhoto(userRequest.getPhoto());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setRole(roleService.findByName(RoleEnum.USER.getRoleName()));
        return user;
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

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    private UserDto mapUserToUserDto(User user){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}

