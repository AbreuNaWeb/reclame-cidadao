package br.com.principal.constante;

public enum MensagemEnum {
	
	ERRO_FAVOR_RETORNAR_PAGINA_INICIAL("Ops! Ocorreu um erro. Favor retornar a página inicial."),
	CPF_JA_CADASTRADO("CPF informado já está cadastrado no sistema."),
	LOGIN_ERRADO("E-mail e/ou senha incorretos. Tente novamente!"),
	ERRO_REALIZAR_LOGIN("Ops! Ocoreu algum erro ao realizar login. Tente novamente!"),
	CADASTROU_SUCESSO("Cadastrou com sucesso!"),
	CPF_INEXISTENTE("O CPF informado não está cadastrado no sistema."),
	CPF_INFORMADO_NAO_PERTENCE_AGENTE("O CPF informado não pertence a um agente."),
	RECLAMACAO_SUGESTAO_ABERTA_AGENTE("Existe reclamações e/ou sugestões abertas atríbuidas ao agente."),
	ERRO_DESCONHECIDO("Opss! Aconteceu algum erro desconhecido. Tente novamente mais tarde."),
	AGENTE_EXCLUIDO_SUCESSO("Agente excluído com sucesso!"),
	AGENTE_ATUALIZADO_SUCESSO("Agente atualizado com sucesso!"),
	DADOS_ATUALIZADOS_SUCESSO("Dados atualizados com sucesso."),
	CELULAR_INVALIDO("Celular inválido."),
	ATINGIU_LIMITE_RECLAMACOES_SUGESTOES_ABERTAS("Você atingiu o limite de seis reclamações e/ou sugestões abertas. Aguarde elas serem resolvidas."),
	ATINGIU_LIMITE_RECLAMACOES_SUGESTOES_CADASTRADAS_DIA("Você atingiu o limite de duas reclamações e/ou sugestões cadastradas por dia.");
	
	private final String descricao;
	
	private MensagemEnum(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}