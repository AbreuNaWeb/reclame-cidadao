package br.com.principal.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "Cidadao")
@Entity
public class CidadaoEntidade extends Pessoa {
	
	@Column(name = "DDD")
	private Byte ddd;
	
	@Column(name = "NUMERO_CELULAR")
	private Integer numeroCelular;
	
	@Column(name = "STATUS")
	private String status;

	public Byte getDdd() {
		return ddd;
	}

	public void setDdd(Byte ddd) {
		this.ddd = ddd;
	}

	public Integer getNumeroCelular() {
		return numeroCelular;
	}

	public void setNumeroCelular(Integer numeroCelular) {
		this.numeroCelular = numeroCelular;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}


