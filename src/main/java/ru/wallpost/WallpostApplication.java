package ru.wallpost;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

@SpringBootApplication
public class WallpostApplication {

	public static void main(String[] args) {
		//SpringApplication.run(WallpostApplication.class, args);

	}

}
