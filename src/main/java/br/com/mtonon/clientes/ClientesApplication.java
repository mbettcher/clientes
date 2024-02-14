package br.com.mtonon.clientes;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.mtonon.clientes.model.entity.Cliente;
import br.com.mtonon.clientes.repository.ClienteRepository;

@SpringBootApplication
public class ClientesApplication {


	@Bean
	public CommandLineRunner run(@Autowired ClienteRepository repo) {
		return args -> {
			Cliente cliente = Cliente.builder()
								.nome("Cliente Teste")
								.cpf("88539489066")
								.dataCadastro(LocalDate.now())
								.build();
			repo.save(cliente);
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ClientesApplication.class, args);
	}

}
