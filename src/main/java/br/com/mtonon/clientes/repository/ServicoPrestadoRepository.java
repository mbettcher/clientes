package br.com.mtonon.clientes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.mtonon.clientes.model.entity.Cliente;
import br.com.mtonon.clientes.model.entity.ServicoPrestado;


public interface ServicoPrestadoRepository extends JpaRepository<ServicoPrestado, Long> {
	
	public List<ServicoPrestado> findByCliente(Cliente cliente);
	
	@Query(" SELECT s FROM ServicoPrestado s JOIN s.cliente c " + 
			"WHERE UPPER(c.nome) LIKE UPPER(:nome) AND MONTH(s.data) = :mes ")
	public List<ServicoPrestado> findByNomeClienteAndMes(@Param("nome") String nome, @Param("mes") Integer mes);

}
