package br.com.gamestore.loja_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class LojaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LojaApiApplication.class, args);
	}

}
