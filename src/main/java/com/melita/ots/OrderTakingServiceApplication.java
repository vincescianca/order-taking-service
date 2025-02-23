package com.melita.ots;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
public class OrderTakingServiceApplication {

	public static void main(String[] args) {
		ConfigurableEnvironment environment = SpringApplication.run(OrderTakingServiceApplication.class, args).getEnvironment();
	}
}