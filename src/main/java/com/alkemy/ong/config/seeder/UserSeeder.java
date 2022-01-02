
package com.alkemy.ong.config.seeder;


import com.alkemy.ong.model.Role;
import com.alkemy.ong.model.User;
import com.alkemy.ong.repository.RoleRepository;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.alkemy.ong.security.RoleEnum;
import java.time.LocalDateTime;
import java.util.Date;

@Component
@AllArgsConstructor
public class UserSeeder implements CommandLineRunner{
    
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    
    
    @Override
    public void run(String... args){
        loadUsers();
    }
    
    private void loadUsers(){
        if (roleRepository.count() < 2) {
            createRoles();
        }
        if (userRepository.count() == 0) {
            createUsers();
        }
    }

    private void createRoles(){
        if(roleRepository.findByName("ADMIN").isEmpty()){
            roleRepository.save(createRoleAdmin());
        }
        if(roleRepository.findByName("USER").isEmpty()) {
            roleRepository.save(createRoleUser());
        }
    }
    
    private void createUsers(){
            userRepository.save(buildUserAdmin("mateo","lopez","mateolopez@gmail.com","useradmin"));
            userRepository.save(buildUserAdmin("garbiel","suarez","gabrielsuarez@gmail.com","useradmin"));
            userRepository.save(buildUserAdmin("rodrigo","sanchez","rodrigosanchez@gmail.com","useradmin"));
            userRepository.save(buildUserAdmin("bruno","almeida","brunoalmeida@gmail.com","useradmin"));
            userRepository.save(buildUserAdmin("julian","delcanto","juliandelcanto@gmail.com","useradmin"));
            userRepository.save(buildUserAdmin("fernanda","ortiz","fernandaortiz@gmail.com","useradmin"));
            userRepository.save(buildUserAdmin("luciana","pomilio","lucianapomilio@gmail.com","useradmin"));
            userRepository.save(buildUserAdmin("gabriela","michelini","gabrielamichelini@gmail.com","useradmin"));
            userRepository.save(buildUserAdmin("georgina","zenteno","georginazenteno@gmail.com","useradmin"));
            userRepository.save(buildUserAdmin("lucia","lopez","lucialopez@gmail.com","useradmin"));
                     
            userRepository.save(buildUser("julia","ledesma","julialedesma@gmail.com","useruser"));
            userRepository.save(buildUser("paula","sanchez","paulasanchez@gmail.com","useruser"));
            userRepository.save(buildUser("natalia","perez","nataliaperez@gmail.com","useruser"));
            userRepository.save(buildUser("clara","grivarello","claragrivarello@gmail.com","useruser"));
            userRepository.save(buildUser("sabrina","izquierdoz","sabrinaizquierdoz@gmail.com","useruser"));
            userRepository.save(buildUser("martin","riquelme","martinriquelme@gmail.com","useruser"));
            userRepository.save(buildUser("horacio","orellano","horacioorellena@gmail.com","useruser"));
            userRepository.save(buildUser("juan","palermo","juanpalermo@gmail.com","useruser"));
            userRepository.save(buildUser("gaston","gutierrez","gastongutierrez@gmail.com","useruser"));
            userRepository.save(buildUser("pablo","ginobili","pabloginobili@gmail.com","useruser"));

    }
    
    private Role createRoleUser(){
        Role roleUser= new Role();
        roleUser.setDescription("USER");
        roleUser.setName("USER");
        roleUser.setRoleEnum(RoleEnum.USER);
        roleUser.setTimestamp(new Date());
        return roleUser;
    }
    
    private Role createRoleAdmin(){
        Role roleAdmin= new Role();
        roleAdmin.setDescription("ADMIN");
        roleAdmin.setName("ADMIN");
        roleAdmin.setRoleEnum(RoleEnum.ADMIN);
        roleAdmin.setTimestamp(new Date());
        return roleAdmin;
    }
    
    private User buildUser(String name, String lastName, String email, String password) {
        User user = new User();
        user.setFirstName(name);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPhoto(null);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(roleService.findByName(RoleEnum.USER.getName()));
        user.setDateCreation(LocalDateTime.now());
        return user; 
    }
    
    private User buildUserAdmin(String name, String lastName, String email, String password) {
        User user = new User();
        user.setFirstName(name);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPhoto(null);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(roleService.findByName(RoleEnum.ADMIN.getName()));
        user.setDateCreation(LocalDateTime.now());
        return user; 
    }
    
}
