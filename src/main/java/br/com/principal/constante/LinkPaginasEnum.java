package br.com.principal.constante;

public enum LinkPaginasEnum {

	LOGIN("login.xhtml");
	
	private final String descricao;

	private LinkPaginasEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}