package com.alkemy.ong.security.service;

import com.alkemy.ong.model.User;
import com.alkemy.ong.security.RoleEnum;
import com.alkemy.ong.security.util.SecurityUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetailsImpl implements UserDetails{

	private static final long serialVersionUID = 1L;
	
	private Long id;
    private String username;
    transient private String password;
    transient private User user;
    private Set<GrantedAuthority> authorities;	
	
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

	public static UserDetailsImpl createSuperUser() {
        Set<GrantedAuthority> authorities = Set.of(SecurityUtils.convertToAuthority(RoleEnum.SYSTEM_MANAGER.name()));
        return UserDetailsImpl.builder()
                .id(-1L)
                .username("system-admin")
                .authorities(authorities)
                .build();
	}

}
