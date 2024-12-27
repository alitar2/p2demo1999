package com.revature.P2Demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.revature.models")
@ComponentScan("com.revature")//tells Spring to scan in models package for DB Entities, annotated with @Entity
@EnableJpaRepositories("com.revature.DAOs") //tells Spring to scan in DAOs package for JPA repositories
public class P2DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(P2DemoApplication.class, args);
		System.out.println("Hey!!!");
	}
}
