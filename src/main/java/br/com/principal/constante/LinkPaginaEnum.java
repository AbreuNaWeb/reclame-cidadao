package br.com.principal.constante;

public enum LinkPaginaEnum {

	LOGIN("login.xhtml"),
	HOME("/home.xhtml?faces-redirect=true"),
	PAGINA_ATUAL("");
	
	private final String link;

	private LinkPaginaEnum(String descricao) {
		this.link = descricao;
	}

	public String getLink() {
		return link;
	}
}