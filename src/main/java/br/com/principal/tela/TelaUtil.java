package br.com.principal.tela;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.com.principal.constante.MensagemDeErroEnum;

public class TelaUtil {
	
	TelaUtil() {
	}

	static void adicionarMensagemDeErro(MensagemDeErroEnum mensagemDeErroEnum) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagemDeErroEnum.getDescricao(), null));
	}
	
	static boolean campoNaoInformado(Object... campos) {
		for (Object campo : campos) {
			if (campo == null) {
				return true;
			}
		}
		
		return false;
	}
}