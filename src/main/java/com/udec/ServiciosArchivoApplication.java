package com.udec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ServiciosArchivoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiciosArchivoApplication.class, args);
	}
}
