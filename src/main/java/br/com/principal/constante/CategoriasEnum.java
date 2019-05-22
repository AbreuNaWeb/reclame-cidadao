package br.com.principal.constante;

public enum CategoriasEnum {

	TRANSPORTE_PUBLICO((byte) 1, "Transporte Público"), 
	ILUMINACAO_PUBLICA((byte) 2, "Iluminação Pública"),
	LIMPEZA_URBANA((byte) 3, "Limpeza Urbana"),
	AGUA_ESGOTO((byte) 4, "Água e Esgoto"),
	SAUDE((byte) 5, "Saúde"),
	SEGURANCA((byte) 6, "Segurança"),
	EDUCACAO((byte) 7, "Educação"),
	OUTROS((byte) 8, "Outros");

	private final Byte codigo;
	private final String descricao;

	private CategoriasEnum(Byte codigo, String descricao) {
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