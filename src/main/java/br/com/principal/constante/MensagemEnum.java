package br.com.principal.constante;

public enum MensagemEnum {
	
	CADASTROU_SUCESSO("Cadastrou com sucesso!"),
	AGENTE_EXCLUIDO_SUCESSO("Agente excluído com sucesso!"),
	AGENTE_ATUALIZADO_SUCESSO("Agente atualizado com sucesso!"),
	DADOS_ATUALIZADOS_SUCESSO("Dados atualizados com sucesso."),
	CIDADAO_BLOQUEADO_SUCESSO("Cidadão bloqueado com sucesso!");
	
	private final String descricao;
	
	private MensagemEnum(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}