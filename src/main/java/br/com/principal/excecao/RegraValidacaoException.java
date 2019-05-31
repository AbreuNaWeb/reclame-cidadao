package br.com.principal.excecao;

import br.com.principal.constante.MensagemEnum;

public class RegraValidacaoException extends Exception {
	
	private static final long serialVersionUID = 1L;

	private final MensagemEnum mensagemEnum;
	
	public RegraValidacaoException(MensagemEnum mensagemEnum) {
		super(mensagemEnum.getDescricao());
		this.mensagemEnum = mensagemEnum;
	}
	
	public MensagemEnum getMensagemEnum() {
		return mensagemEnum;
	}
}