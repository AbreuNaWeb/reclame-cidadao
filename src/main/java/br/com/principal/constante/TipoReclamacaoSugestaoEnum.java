package br.com.principal.constante;

public enum TipoReclamacaoSugestaoEnum {

	RECLAMACAO((byte) 1, "Reclamação"), 
	SUGESTAO((byte) 2, "Sugestão");

	private final Byte codigo;
	private final String descricao;

	private TipoReclamacaoSugestaoEnum(Byte codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Byte getCodigo() {
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
}