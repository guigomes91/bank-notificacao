package br.com.gomes.banknotificacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BankNotificacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankNotificacaoApplication.class, args);
	}

}
