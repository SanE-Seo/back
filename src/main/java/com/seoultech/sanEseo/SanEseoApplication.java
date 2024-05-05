package com.seoultech.sanEseo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SanEseoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SanEseoApplication.class, args);
	}

}
