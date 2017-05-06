package com.example.scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaRepositories(basePackages ="com.example.scheduler.repository")
@EntityScan(basePackages = "com.example.scheduler.domain")
@EnableScheduling
@EnableAutoConfiguration 
public class SchedulerTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchedulerTestApplication.class, args);
	}
}
