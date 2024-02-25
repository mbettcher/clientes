package br.com.mtonon.clientes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.mtonon.clientes.model.entity.Cliente;
import br.com.mtonon.clientes.model.entity.ServicoPrestado;
import br.com.mtonon.clientes.repository.ClienteRepository;
import br.com.mtonon.clientes.repository.ServicoPrestadoRepository;

@Service
public class ServicoPrestadoService {
	
	@Autowired
	private ServicoPrestadoRepository repository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	

	public ServicoPrestado buscarPorId(Long id) {
		return this.repository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Servico Prestado não localizado!"));
	}

	
	
	public ServicoPrestado salvar(ServicoPrestado servicoPrestado) {

		Cliente cliente = clienteRepository.findById(servicoPrestado.getCliente().getId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente não localizado!"));

		servicoPrestado.setCliente(cliente);
		return this.repository.save(servicoPrestado);
	
	}
	
	
	
	public List<ServicoPrestado> listar(){
		return this.repository.findAll();
	}
	
	
	
	public List<ServicoPrestado> listarPorCliente(Cliente cliente) {
		return this.repository.findByCliente(cliente);
	}
	
	
	
	public List<ServicoPrestado> findByNomeClienteAndMes(String nome, Integer mes){
		return this.repository.findByNomeClienteAndMes( "%"+nome+"%", mes);
	}
}
