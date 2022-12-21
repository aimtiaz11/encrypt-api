package com.encrypt.encryptapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.Arrays;

@EnableSwagger2WebMvc
@SpringBootApplication
public class EncryptApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EncryptApiApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			System.out.println("Listing encryption key properties:");
			System.getProperties()
					.stringPropertyNames()
					.stream()
					.filter(prop -> StringUtils.startsWithIgnoreCase(prop, "encryption.key."))
					.forEach(prop -> System.out.println(prop));

			System.out.println("=== End of List ===");
		};
	}


}
