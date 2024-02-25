package br.com.mtonon.clientes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.mtonon.clientes.model.entity.Cliente;
import br.com.mtonon.clientes.model.entity.ServicoPrestado;
import br.com.mtonon.clientes.model.entity.dto.ServicoPrestadoDTO;
import br.com.mtonon.clientes.service.ClienteService;
import br.com.mtonon.clientes.service.ServicoPrestadoService;
import br.com.mtonon.clientes.util.BigDecimalConverter;

@RestController
@RequestMapping(value = "/api/servicos-prestados")
public class ServicoPrestadoController {

	@Autowired
	private ServicoPrestadoService servicoPrestadoService;
	
	@Autowired
	private BigDecimalConverter bigDecimalConverter;
	
	@Autowired
	private ClienteService clienteService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ServicoPrestado salvar(@RequestBody ServicoPrestadoDTO servicoPrestadoDTO) {
		ServicoPrestadoDTO servicoDTO = servicoPrestadoDTO;
		ServicoPrestado servicoPrestado = servicoDTO.fromDTO();
		servicoPrestado.setValor(this.bigDecimalConverter.converter(servicoPrestadoDTO.getValor()));
		return this.servicoPrestadoService.salvar(servicoPrestado);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ServicoPrestado> listar(){
		return this.servicoPrestadoService.listar();
	}
	
	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public List<ServicoPrestado> listarPorCliente(@PathVariable Long id) {
		Cliente cliente = this.clienteService.buscarPorId(id);
		return this.servicoPrestadoService.listarPorCliente(cliente);
	}
	
	@GetMapping(value = "/pesquisar")
	@ResponseStatus(HttpStatus.OK)
	public List<ServicoPrestado> pesquisar(
			@RequestParam(value = "nome", required = false, defaultValue = "") String nome,
			@RequestParam(value = "mes") Integer mes
	){
		return this.servicoPrestadoService.findByNomeClienteAndMes(nome, mes);
	}
}
