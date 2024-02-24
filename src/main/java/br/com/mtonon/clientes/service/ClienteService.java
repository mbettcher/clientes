package br.com.mtonon.clientes.service;

import java.time.LocalDate;
import java.util.List;

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
	
	public List<Cliente> listarTodos() {
		return this.clienteRepository.findAll();
	}
	
	public Cliente salvarCliente(Cliente cliente) {
		cliente.setDataCadastro(LocalDate.now());
		return this.clienteRepository.save(cliente);
	}
	
	public Cliente buscarPorId(Long id) {
		return this.clienteRepository.findById(id).orElseThrow(
					() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não localizado!")
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
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não localizado!")
			);
	}
	
	public void atualizarCliente(Long id, Cliente cliente) {
		this.clienteRepository
		.findById(id)
		.map(clienteOld -> {
			this.updateData(clienteOld, cliente);
			return this.clienteRepository.save(clienteOld);
		})
		.orElseThrow(
			() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não localizado!")
		);
	}
	
	private void updateData(Cliente clienteOld, Cliente clienteNew) {
		clienteOld.setNome(clienteNew.getNome());
		clienteOld.setCpf(clienteNew.getCpf());
	}
}
