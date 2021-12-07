package com.alkemy.ong.security;

public interface SecurityConstant {

	public static final String ADMIN = "hasAnyRole(T(com.alkemy.ong.security.RoleEnum).ADMIN)";
	public static final String USER = "hasAnyRole(T(com.alkemy.ong.security.RoleEnum).USER)";
}

