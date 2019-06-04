package br.com.principal.constante;

public enum MensagemEnum {
	
	INFORAR_CAMPOS_ENDERECO("Foi informado o estado, então deve ser preenchido todos campos obrigatórios de endereço."),
	ERRO_FAVOR_RETORNAR_PAGINA_INICIAL("Ops! Ocorreu um erro. Favor retornar a página inicial."),
	CPF_JA_CADASTRADO("CPF informado já está cadastrado no sistema."),
	LOGIN_ERRADO("E-mail e/ou senha incorretos. Tente novamente!"),
	ERRO_REALIZAR_LOGIN("Ops! Ocoreu algum erro ao realizar login. Tente novamente!"),
	CADASTROU_SUCESSO("Cadastrou com sucesso!"),
	CPF_INEXISTENTE("O CPF informado não está cadastrado no sistema."),
	CPF_INFORMADO_NAO_PERTENCE_AGENTE("O CPF informado não pertence a um agente."),
	RECLAMACAO_SUGESTAO_ABERTA_AGENTE("Existe reclamações e/ou sugestões abertas atríbuidas ao agente."),
	ERRO_DESCONHECIDO("Opss! Aconteceu algum erro desconhecido."),
	AGENTE_EXCLUIDO_SUCESSO("Agente excluído com sucesso!"),
	AGENTE_ATUALIZADO_SUCESSO("Agente atualizado com sucesso!"),
	DADOS_ATUALIZADOS_SUCESSO("Dados atualizados com sucesso.");
	
	private final String descricao;
	
	private MensagemEnum(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}