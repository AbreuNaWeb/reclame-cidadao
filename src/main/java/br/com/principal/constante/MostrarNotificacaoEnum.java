package br.com.principal.constante;

import br.com.principal.entidade.UsuarioEntidade;

public enum MostrarNotificacaoEnum {

	SIM("Sim"),
	NAO("Não");
	
	private final String descricao;

	private MostrarNotificacaoEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public boolean igual(UsuarioEntidade usuarioEntidade) {
		return this.descricao.equals(usuarioEntidade.getMostrarNotificacao());
	}
}