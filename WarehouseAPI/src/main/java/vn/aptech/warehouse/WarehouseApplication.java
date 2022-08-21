package vn.aptech.warehouse;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import java.io.IOException;
import java.util.ArrayList;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
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
        @Bean
        FirebaseMessaging firebaseMessaging() throws IOException {
            GoogleCredentials googleCredentials = GoogleCredentials
                    .fromStream(new ClassPathResource("firebase-service-account.json").getInputStream());
            FirebaseOptions firebaseOptions = FirebaseOptions
                    .builder()
                    .setCredentials(googleCredentials)
                    .build();
            FirebaseApp app = FirebaseApp.initializeApp(firebaseOptions, "my-app");
            return FirebaseMessaging.getInstance(app);
        }
//        @Bean
//        CommandLineRunner run(UserService userService){
//            return args ->{
//                userService.saveRole(new Role(0,"ROLE_USER"));
//                userService.saveRole(new Role(0,"ROLE_MANAGER"));
//                userService.saveRole(new Role(0,"ROLE_ADMIN"));
//           
//                  userService.saveUser(new User(0,"long", "long@gmail.com", "123", new ArrayList<>(),true));
//                userService.saveUser(new User(0,"hien", "hien@gmail.com", "123", new ArrayList<>(),true));
//                userService.saveUser(new User(0,"dung", "dung@gmail.com", "123", new ArrayList<>(),true));
//                userService.saveUser(new User(0,"tuan", "tuan@gmail.com", "123", new ArrayList<>(),true));
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
