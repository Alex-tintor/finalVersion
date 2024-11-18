package com.portfolio.finalversion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;

@SpringBootApplication
@EnableWebFluxSecurity
public class FinalversionApplication {

	public static void main(String[] args) {
		System.setProperty("log4j.skipJansi", "false");
		System.setProperty("log4j2.enable.ansi", "true");
		SpringApplication.run(FinalversionApplication.class, args);
	}

}
