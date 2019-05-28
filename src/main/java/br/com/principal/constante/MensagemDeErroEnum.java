package br.com.principal.constante;

public enum MensagemDeErroEnum {
	
	INFORAR_CAMPOS_ENDERECO("Foi informado o estado, então deve ser preenchido todos campos obrigatórios de endereço."),
	ERRO_FAVOR_RETORNAR_PAGINA_INICIAL("Ops! Ocorreu um erro. Favor retornar a página inicial."),
	CPF_JA_CADASTRADO("CPF informado já está cadastrado no sistema."),
	LOGIN_ERRADO("E-mail e/ou senha incorretos. Tente novamente!"),
	ERRO_REALIZAR_LOGIN("Ops! Ocoreu algum erro ao realizar login. Tente novamente!");
	
	private final String descricao;
	
	private MensagemDeErroEnum(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}