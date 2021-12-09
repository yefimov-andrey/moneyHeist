package com.testtask.moneyheist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.testtask.moneyheist.repositories")
public class MoneyheistApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoneyheistApplication.class, args);
	}

}
