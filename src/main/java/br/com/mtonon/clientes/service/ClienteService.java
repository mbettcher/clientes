package br.com.mtonon.clientes.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
	
	public Cliente buscarPorId(Long id) {
		return this.clienteRepository.findById(id).orElseThrow(
					() -> new ResponseStatusException(HttpStatus.NOT_FOUND)
				);
	}
	
	public void deletarCliente(Long id) {
		this.clienteRepository
			.findById(id)
			.map(cliente -> {
				this.clienteRepository.deleteById(id);
				return Void.TYPE;
			})
			.orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND)
			);
	}
}
