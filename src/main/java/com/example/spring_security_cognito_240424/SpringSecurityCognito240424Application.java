package com.example.spring_security_cognito_240424;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// http://localhost:8080/

@SpringBootApplication
public class SpringSecurityCognito240424Application {

	public static void main(String[] args) {

		Dotenv dotenv = Dotenv.load();

		System.setProperty("spring.security.oauth2.client.registration.cognito.clientId", dotenv.get("CLIENT_ID"));
		System.setProperty("spring.security.oauth2.client.registration.cognito.clientSecret", dotenv.get("CLIENT_SECRET"));
		System.setProperty("spring.security.oauth2.client.provider.cognito.issuerUri", dotenv.get("ISSUER_URI"));
		System.setProperty("aws.cognito.poolId", dotenv.get("POOL_ID"));
		System.setProperty("aws.cognito.registration.clientId", dotenv.get("CLIENT_ADMIN_ID"));

		System.setProperty("spring.datasource.url", dotenv.get("DATABASE_URL"));
		System.setProperty("spring.datasource.username", dotenv.get("DATABASE_USERNAME"));
		System.setProperty("spring.datasource.password", dotenv.get("DATABASE_PASSWORD"));

		SpringApplication.run(SpringSecurityCognito240424Application.class, args);
	}

}
