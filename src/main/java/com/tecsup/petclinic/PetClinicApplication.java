package com.tecsup.petclinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 
 * @author jgomezm
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.tecsup.petclinic.controllers", "com.tecsup.petclinic.services"})
public class PetClinicApplication {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(PetClinicApplication.class, args);
	}

}
