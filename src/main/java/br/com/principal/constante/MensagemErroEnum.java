package br.com.principal.constante;

public enum MensagemErroEnum {

	ERRO_DESCONHECIDO("Opss! Aconteceu algum erro desconhecido. Tente novamente mais tarde."),
	ATINGIU_LIMITE_RECLAMACOES_SUGESTOES_ABERTAS("Você atingiu o limite de seis reclamações e/ou sugestões abertas. Aguarde elas serem resolvidas."),
	ATINGIU_LIMITE_RECLAMACOES_SUGESTOES_CADASTRADAS_DIA("Você atingiu o limite de duas reclamações e/ou sugestões cadastradas por dia."),
	CPF_JA_CADASTRADO("CPF informado já está cadastrado no sistema."),
	LOGIN_ERRADO("E-mail e/ou senha incorretos. Tente novamente!"),
	ERRO_REALIZAR_LOGIN("Ops! Ocoreu algum erro ao realizar login. Tente novamente!"),
	ERRO_FAVOR_RETORNAR_PAGINA_INICIAL("Ops! Ocorreu um erro. Favor retornar a página inicial."),
	CPF_INEXISTENTE("O CPF informado não está cadastrado no sistema."),
	CPF_INFORMADO_NAO_PERTENCE_AGENTE("O CPF informado não pertence a um agente."),
	RECLAMACAO_SUGESTAO_ABERTA_AGENTE("Existe reclamações e/ou sugestões abertas atríbuidas ao agente."),
	CELULAR_INVALIDO("Celular inválido."),
	CPF_INFORMADO_NAO_PERTENCE_CIDADAO("O CPF informado não pertence a um cidadão."),
	CIDADAO_JA_BLOQUEADO("Cidadão já está bloqueado."),
	VOCE_NAO_TEM_PERMISSAO("Você não tem permissão para acessar a página!"),
	VOCE_FOI_BLOQUEADO("Você foi bloqueado por violar as regras do Reclame Cidadão.");
	
	private final String descricao;

	private MensagemErroEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}