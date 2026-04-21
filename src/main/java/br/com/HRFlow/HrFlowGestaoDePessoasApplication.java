package br.com.HRFlow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class HrFlowGestaoDePessoasApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrFlowGestaoDePessoasApplication.class, args);
	}

}
