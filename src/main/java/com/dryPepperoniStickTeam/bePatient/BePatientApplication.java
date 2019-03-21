package com.dryPepperoniStickTeam.bePatient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
@EnableJpaRepositories
public class BePatientApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(BePatientApplication.class, args);
	}

}
