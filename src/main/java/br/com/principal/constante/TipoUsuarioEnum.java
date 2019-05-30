package br.com.principal.constante;

import br.com.principal.entidade.PessoaEntidade;

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
	
	public boolean igual(PessoaEntidade pessoaRegistro) {
		return getDescricao().equals(pessoaRegistro.getTipo());
	}
}