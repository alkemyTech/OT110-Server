package com.alkemy.ong.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alkemy.ong.model.User;
import com.alkemy.ong.service.IUserService;
import com.alkemy.ong.util.SecurityUtils;

import lombok.extern.log4j.Log4j2;

import java.util.Set;

@Log4j2
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    	log.info("[CustomUserDetailsService] -> loadUserByUsername ");
        User user = userService.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
        
        log.info("[CustomUserDetailsService] -> authorities ");
        Set<GrantedAuthority> authorities = Set.of(SecurityUtils.convertToAuthority(user.getRole().getName()));

        log.info("[CustomUserDetailsService] -> UserDetailsImpl.builder() ");
        return UserDetailsImpl.builder()
                .user(user).id(user.getUserId())
                .username(email)
                .password(user.getPassword())
                .authorities(authorities)
                .build();
    }

}
