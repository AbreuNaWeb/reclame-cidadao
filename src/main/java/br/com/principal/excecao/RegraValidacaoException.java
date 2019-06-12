package br.com.principal.excecao;

import br.com.principal.constante.MensagemErroEnum;

public class RegraValidacaoException extends Exception {
	
	private static final long serialVersionUID = 1L;

	private final MensagemErroEnum mensagemErroEnum;
	
	public RegraValidacaoException(MensagemErroEnum mensagemErroEnum) {
		super(mensagemErroEnum.getDescricao());
		this.mensagemErroEnum = mensagemErroEnum;
	}
	
	public MensagemErroEnum getMensagemErroEnum() {
		return mensagemErroEnum;
	}
}