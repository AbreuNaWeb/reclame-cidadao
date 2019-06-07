package br.com.principal.constante;

import br.com.principal.entidade.ReclamacaoSugestaoEntidade;

public enum StatusReclamacaoSugestaoEnum {

	ABERTA("Aberta"),
	EM_ANALISE("Em Análise"),
	INDEFERIDA("Indeferida"),
	CONCLUIDA("Concluída");

	private final String descricao;

	private StatusReclamacaoSugestaoEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public boolean igual(ReclamacaoSugestaoEntidade reclamacaoSugestaoRegistro) {
		return getDescricao().equals(reclamacaoSugestaoRegistro.getStatus());
	}
}