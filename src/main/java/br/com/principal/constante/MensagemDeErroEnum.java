package br.com.principal.constante;

public enum MensagemDeErroEnum {
	
	INFORAR_CAMPOS_ENDERECO("Foi informado o estado, então deve ser preenchido todos campos obrigatórios de endereço.");
	
	private final String descricao;
	
	private MensagemDeErroEnum(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}