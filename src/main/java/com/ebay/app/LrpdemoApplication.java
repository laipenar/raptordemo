package com.ebay.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
public class LrpdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LrpdemoApplication.class, args);
	}
}
