package com.alkemy.ong.security;

public enum RoleEnum {
	ADMIN("ADMIN"), USER("USER"), SYSTEM_MANAGER("SYSTEM_MANAGER");

	private final String name;
	private static final String ROLE_ = "ROLE_";

	RoleEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getRoleName() {
		return ROLE_ + name;
	}
}
