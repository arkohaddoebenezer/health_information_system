package com.hospitalinformationsystem.his;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class HISApplication {

	public static void main(String[] args) {
		SpringApplication.run(HISApplication.class, args);
	}

}
