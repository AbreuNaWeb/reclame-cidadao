package br.com.principal.constante;

import br.com.principal.entidade.PessoaEntidade;

public enum TipoPessoaEnum {

	CIDADAO("Cidadão"),
	AGENTE("Agente");

	private final String descricao;

	private TipoPessoaEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public boolean igual(PessoaEntidade pessoaRegistro) {
		return getDescricao().equals(pessoaRegistro.getTipo());
	}
}