package br.com.principal.constante;

import br.com.principal.entidade.UsuarioEntidade;

public enum TipoUsuarioEnum {

	CIDADAO("Cidadão"),
	AGENTE("Agente"),
	ADMINISTRADOR("Administrador");

	private final String descricao;

	private TipoUsuarioEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public boolean igual(UsuarioEntidade pessoaRegistro) {
		return pessoaRegistro != null && getDescricao().equals(pessoaRegistro.getTipo());
	}
}