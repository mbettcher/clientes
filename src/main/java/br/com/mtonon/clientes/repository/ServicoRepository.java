package br.com.mtonon.clientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mtonon.clientes.model.entity.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Long> {

}
