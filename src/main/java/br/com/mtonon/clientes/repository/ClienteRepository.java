package br.com.mtonon.clientes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mtonon.clientes.model.entity.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	public List<Cliente> findByCpf(String cpf);

}
