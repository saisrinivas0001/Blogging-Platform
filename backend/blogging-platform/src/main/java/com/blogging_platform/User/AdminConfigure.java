package com.blogging_platform.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.blogging_platform.enum_class.Role;

@Component
public class AdminConfigure implements CommandLineRunner {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        String adminUsername = "sai_admin";
        String adminEmail = "saisrinivas.b.official@gmail.com";

        // Check if admin already exists
        if (userRepo.findUserByUsername(adminUsername).isEmpty()) {
            User admin = new User();
            admin.setUsername(adminUsername);
            admin.setEmail(adminEmail);
            admin.setPassword(passwordEncoder.encode("SaiSrinivas@12345")); // default password
            admin.setRole(Role.ADMIN);

            userRepo.save(admin);
            System.out.println("Admin user created successfully!");
        } else {
            System.out.println("Admin user already exists, skipping creation.");
        }
    }
}
