package br.com.principal.tela.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import br.com.principal.constante.MensagemEnum;

public class TelaUtil {
	
	TelaUtil() {
	}

	public static void adicionarMensagemDeErro(MensagemEnum mensagemEnum) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
				mensagemEnum.getDescricao(), null));
	}
	
	public static void adicionarMensagemDeInformacao(MensagemEnum mensagemEnum) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
				mensagemEnum.getDescricao(), null));
	}
	
	public static void redirecionarParaOutraPagina(String url, String mensagem) {
		try {
			FacesContext contextoAtual = FacesContext.getCurrentInstance();
			ExternalContext contextoExterno = contextoAtual.getExternalContext();
			Flash memoria = contextoExterno.getFlash();
			memoria.setKeepMessages(true);
			contextoAtual.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
					mensagem, null));
			contextoExterno.redirect(url);
		} catch (IOException excecao) {
			adicionarMensagemDeErro(MensagemEnum.ERRO_FAVOR_RETORNAR_PAGINA_INICIAL);
		}
	}
	
	public static boolean campoNaoInformado(Object... campos) {
		for (Object campo : campos) {
			if (campo == null) {
				return true;
			}
		}
		
		return false;
	}
	
	public static String diaAtualEmFormatoDiaMesAno() {
		DateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		return formatoData.format(Calendar.getInstance().getTime());
	}
	
	public static String converterCalendarParaHoraMinuto(Calendar calendar) {
		DateFormat formatoData = new SimpleDateFormat("HH:mm");
		return formatoData.format(calendar.getTime());
	}
}