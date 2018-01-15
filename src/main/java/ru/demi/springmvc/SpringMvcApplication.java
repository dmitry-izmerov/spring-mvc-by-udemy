package ru.demi.springmvc;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMvcApplication.class, args);
	}

	@Bean
	public StrongPasswordEncryptor passwordEncryptor() {
		return new StrongPasswordEncryptor();
	}
}
