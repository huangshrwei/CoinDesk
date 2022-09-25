package com.coinmill;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableJpaAuditing
@Slf4j
public class CoinMillApplication {

	public static void main(String[] args){
		SpringApplication.run(CoinMillApplication.class, args);
	}

}
