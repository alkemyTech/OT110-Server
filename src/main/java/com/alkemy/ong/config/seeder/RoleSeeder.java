package com.alkemy.ong.config.seeder;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.alkemy.ong.model.Role;
import com.alkemy.ong.repository.RoleRepository;
import com.alkemy.ong.security.RoleEnum;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class RoleSeeder implements CommandLineRunner {

	private final RoleRepository roleRepository;

	@Override
	public void run(String... args) throws Exception {
		loadRoles();
	}

	private void loadRoles() {
		if (roleRepository.count() == 0) {
			loadRolesSeed();
		}
	}

	private void loadRolesSeed() {
		roleRepository.save(buildRol(RoleEnum.USER.getName(), "Descripcion Rol Usuario", RoleEnum.USER, asDate(LocalDate.now())));
		roleRepository.save(buildRol(RoleEnum.ADMIN.getName(), "Descripcion Rol Administrador", RoleEnum.ADMIN, asDate(LocalDate.now())));
		roleRepository.save(buildRol(RoleEnum.SYSTEM_MANAGER.getName(), "Descripcion Super Usuario", RoleEnum.SYSTEM_MANAGER, asDate(LocalDate.now())));
	}

	private Role buildRol(String name, String descripcion, RoleEnum roleEnum, Date timestamp) {
		Role role = new Role();
		role.setName(name);
		role.setDescription(descripcion);
		role.setRoleEnum(roleEnum);
		role.setTimestamp(timestamp);
		return role;
	}

	public static Date asDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

}
