package br.com.mtonon.clientes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mtonon.clientes.model.entity.Cliente;
import br.com.mtonon.clientes.model.entity.ServicoPrestado;


public interface ServicoPrestadoRepository extends JpaRepository<ServicoPrestado, Long> {
	
	public List<ServicoPrestado> findByCliente(Cliente cliente);

}
