package com.pragma.emazon_transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EmazonTransactionApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmazonTransactionApplication.class, args);
	}

}
