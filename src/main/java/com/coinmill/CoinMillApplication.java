package com.coinmill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CoinMillApplication {

	public static void main(String[] args){
		SpringApplication.run(CoinMillApplication.class, args);
	}

}
