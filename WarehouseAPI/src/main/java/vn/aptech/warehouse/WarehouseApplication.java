package vn.aptech.warehouse;

import java.util.ArrayList;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import vn.aptech.warehouse.entity.Role;
import vn.aptech.warehouse.entity.User;
import vn.aptech.warehouse.service.UserService;

@SpringBootApplication
public class WarehouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(WarehouseApplication.class, args);
	}
        @Bean
        PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
        }
//        @Bean
//        CommandLineRunner run(UserService userService){
//            return args ->{
//                userService.saveRole(new Role(0,"ROLE_USER"));
//                userService.saveRole(new Role(0,"ROLE_MANAGER"));
//                userService.saveRole(new Role(0,"ROLE_ADMIN"));
//           
//                  userService.saveUser(new User(0,"long", "long@gmail.com", "123", new ArrayList<>()));
//                userService.saveUser(new User(0,"hien", "hien@gmail.com", "123", new ArrayList<>()));
//                userService.saveUser(new User(0,"dung", "dung@gmail.com", "123", new ArrayList<>()));
//                userService.saveUser(new User(0,"tuan", "tuan@gmail.com", "123", new ArrayList<>()));
//                    userService.addRoleToUser("long", "ROLE_USER");
//                userService.addRoleToUser("hien", "ROLE_USER");
//                userService.addRoleToUser("hien", "ROLE_ADMIN");
//                userService.addRoleToUser("hien", "ROLE_MANAGER");
//                userService.addRoleToUser("dung", "ROLE_MANAGER");
//                userService.addRoleToUser("tuan", "ROLE_ADMIN");
//                
//            };
//        }

}
