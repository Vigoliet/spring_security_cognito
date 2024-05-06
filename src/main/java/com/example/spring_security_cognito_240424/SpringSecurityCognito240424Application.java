package com.example.spring_security_cognito_240424;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// http://localhost:8080/

@SpringBootApplication
public class SpringSecurityCognito240424Application {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
		String clientId = dotenv.get("CLIENT_ID");
		System.out.println("Client ID: " + clientId);

		String clientSecret = dotenv.get("CLIENT_SECRET");
		System.out.println("Client Secret: " + clientSecret);

		String issuerUri = dotenv.get("ISSUER_URI");
		System.out.println("IssuerURI: " +issuerUri);

		System.setProperty("clientId", clientId);
		System.setProperty("clientSecret", clientSecret);
		System.setProperty("issuerUri", issuerUri);

		SpringApplication.run(SpringSecurityCognito240424Application.class, args);
	}

}
