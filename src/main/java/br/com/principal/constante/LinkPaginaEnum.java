package br.com.principal.constante;

public enum LinkPaginaEnum {

	LOGIN("login.xhtml"),
	DETALHE_RECLAMACAO_SUGESTAO("/reclame-cidadao/detalheReclamacaoSugestao.xhtml?id=");
	
	private final String link;

	private LinkPaginaEnum(String descricao) {
		this.link = descricao;
	}

	public String getLink() {
		return link;
	}
}