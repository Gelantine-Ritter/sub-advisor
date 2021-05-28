package com.subadvisor;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@Log4j2
@SpringBootApplication
public class BackendApplication {

	public static void main(String... args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	ApplicationRunner applicationRunner(Environment env ,
										// $HOME is system-env-varibale. Will print something like /Users/matti
										@Value("${spring.profiles.active}") String profile
										) {
		return args -> {
			// using @Value is better than env
			log.info("Welcome from Application-Runner. Your active profile is " + profile);
		};
	}

}


