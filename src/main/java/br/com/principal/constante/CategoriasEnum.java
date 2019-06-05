package br.com.principal.constante;

public enum CategoriasEnum {

	AGUA_ESGOTO("Água e Esgoto"),
	EDUCACAO("Educação"),
	ILUMINACAO_PUBLICA("Iluminação Pública"),
	LIMPEZA_URBANA("Limpeza Urbana"),
	OUTROS("Outros"),
	SAUDE("Saúde"),
	SEGURANCA("Segurança"),
	TRANSPORTE_PUBLICO("Transporte Público");
	
	private final String descricao;

	private CategoriasEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}