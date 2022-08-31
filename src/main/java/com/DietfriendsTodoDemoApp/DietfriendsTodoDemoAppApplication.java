package com.DietfriendsTodoDemoApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

@EnableJpaAuditing
@SpringBootApplication
public class DietfriendsTodoDemoAppApplication {

//	public static void main(String[] args) {
//		SpringApplication.run(DietfriendsTodoDemoAppApplication.class, args);
//	}

	public static final String APPLICATION_LOCATIONS = "spring.config.additional-location="
			+"classpath:application.yml,"
			+"./app/config/Dietfriends-Todo-Demo-App/springInfo-application.yml";
//			+"app/config/Dietfriends-Todo-Demo-App/springInfo-application.yml";  로컬용

	public static void main(String[] args) {
		new SpringApplicationBuilder(DietfriendsTodoDemoAppApplication.class)
				.properties(APPLICATION_LOCATIONS)
				.run(args);
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}


}
