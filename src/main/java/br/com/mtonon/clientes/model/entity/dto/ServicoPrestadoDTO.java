package br.com.mtonon.clientes.model.entity.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.com.mtonon.clientes.model.entity.Cliente;
import br.com.mtonon.clientes.model.entity.ServicoPrestado;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ServicoPrestadoDTO {
	
	private Long id;
	private String descricao;
	private String data;
	private String valor;
	private Long idCliente;
	
	public ServicoPrestado fromDTO() {
		ServicoPrestado servicoPrestado = new ServicoPrestado();
		servicoPrestado.setId(this.getId() == null ? null : this.getId());
		servicoPrestado.setDescricao(this.getDescricao() == null ? null : this.getDescricao());
		servicoPrestado.setData(this.getData() == null ? null : LocalDate.parse(this.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		Cliente cliente = new Cliente();
		cliente.setId(this.getIdCliente());
		servicoPrestado.setCliente(cliente);
		return servicoPrestado;
	}
	
}
