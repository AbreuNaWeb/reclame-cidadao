package br.com.principal.constante;

public enum CategoriasEnum {

	TRANSPORTE_PUBLICO("Transporte Público"), 
	ILUMINACAO_PUBLICA("Iluminação Pública"),
	LIMPEZA_URBANA("Limpeza Urbana"),
	AGUA_ESGOTO("Água e Esgoto"),
	SAUDE("Saúde"),
	SEGURANCA("Segurança"),
	EDUCACAO("Educação"),
	OUTROS("Outros");

	private final String descricao;

	private CategoriasEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}