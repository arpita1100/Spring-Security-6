package com.app;

import com.app.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AngularOuthExampleApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepo userRepo;
	@Override
	public void run(String... args) throws Exception {
//		System.out.println(passwordEncoder.encode("tanya"));
//		tm197234@gmail.com
//		$2a$10$35lHDcWCP8JQ51lm2V0EzOo08yQ6vDMojYnBN9grajR8Gt0K8fQW2
		System.out.println(userRepo.findByUsername("tm197234@gmail.com"));
		System.out.println(passwordEncoder.encode("google"));
	}

	public static void main(String[] args) {
		SpringApplication.run(AngularOuthExampleApplication.class, args);
	}

}
