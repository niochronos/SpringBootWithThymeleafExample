package com.oliinyk.yaroslav.SpringBootWithThymeleafExample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.oliinyk.yaroslav.repository")
@EntityScan("com.oliinyk.yaroslav.entity")
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class SpringBootWithThymeleafExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWithThymeleafExampleApplication.class, args);
	}

}
