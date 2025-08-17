package com.jobconnect.jobconnect;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.jobconnect.jobconnect.model.Role;
import com.jobconnect.jobconnect.model.User;
import com.jobconnect.jobconnect.repository.UserRepository;

@SpringBootApplication
public class JobConnectApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobConnectApplication.class, args);
	}


//    @Bean
//    CommandLineRunner run(UserRepository userRepository) {
//        return args -> {
//            if (!userRepository.existsByUserName("admin")) {
//                User admin = User.builder()
//                        .userName("admin")
//                        .email("admin@jobconnect.com")
//                        .password(new BCryptPasswordEncoder().encode("admin123"))
//                        .role(Role.ADMIN)
//                        .build();
//                userRepository.save(admin);
//                System.out.println("Admin user created.");
//            }
//        };
//    }
}