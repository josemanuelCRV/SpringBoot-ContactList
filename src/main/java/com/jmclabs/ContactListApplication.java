package com.jmclabs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ContactListApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContactListApplication.class, args);
	}
}
