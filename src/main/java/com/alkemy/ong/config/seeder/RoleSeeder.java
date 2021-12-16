package com.alkemy.ong.config.seeder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.alkemy.ong.model.Role;
import com.alkemy.ong.repository.RoleRepository;
import com.alkemy.ong.security.RoleEnum;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RoleSeeder implements CommandLineRunner {
	
    private final RoleRepository roleRepository;
	
	@Override
	public void run(String... args) throws Exception {
		loadRoles();
	}
	
    private void loadRoles() throws ParseException {
        if (roleRepository.count() == 0) {
            loadRolesSeed();
        }
    }
    
    private void loadRolesSeed() throws ParseException{
    	roleRepository.save(buildRole("USER", RoleEnum.USER, "Rol Usuario", new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.now().toString())));
    	roleRepository.save(buildRole("ADMIN", RoleEnum.ADMIN, "Rol Administrador", new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.now().toString())));
    }    
	
    private Role buildRole(String name, RoleEnum roleEnum, String description, Date timestamp) throws ParseException {
        Role role = new Role();
        role.setName(name);
        role.setRoleEnum(roleEnum);
        role.setDescription(description);
        role.setTimestamp(timestamp);
        return role;
    }    
    
}
