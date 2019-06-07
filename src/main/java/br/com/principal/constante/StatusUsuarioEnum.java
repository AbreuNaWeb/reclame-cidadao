package br.com.principal.constante;

import br.com.principal.entidade.UsuarioEntidade;

public enum StatusUsuarioEnum {

	NORMAL("Normal"),
	BLOQUEADO("Bloqueado");

	private final String descricao;

	private StatusUsuarioEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public boolean igual(UsuarioEntidade usuario) {
		return getDescricao().equals(usuario.getStatus());
	}
}