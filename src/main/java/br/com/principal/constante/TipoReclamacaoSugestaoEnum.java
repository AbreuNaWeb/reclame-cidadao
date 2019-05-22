package br.com.principal.constante;

public enum TipoReclamacaoSugestaoEnum {

	RECLAMACAO("Reclamação"), 
	SUGESTAO("Sugestão");

	private final String descricao;

	private TipoReclamacaoSugestaoEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}