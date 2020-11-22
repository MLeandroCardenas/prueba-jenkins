package com.udec.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Programado {
	
	@Scheduled(fixedRate = 60000)
	public void ejecutar() {
		System.out.println("Bienvenidos alumnos....");
	}

}
