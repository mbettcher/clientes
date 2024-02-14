package br.com.mtonon.clientes.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mtonon.clientes.model.entity.Cliente;
import br.com.mtonon.clientes.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente salvarCliente(Cliente cliente) {
		cliente.setDataCadastro(LocalDate.now());
		return this.clienteRepository.save(cliente);
	}
}
