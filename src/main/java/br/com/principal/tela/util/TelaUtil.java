package br.com.principal.tela.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import br.com.principal.constante.MensagemEnum;
import br.com.principal.constante.MensagemErroEnum;

public class TelaUtil {
	
	TelaUtil() {
	}

	public static void adicionarMensagemDeErro(MensagemErroEnum mensagemErroEnum) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
				mensagemErroEnum.getDescricao(), null));
	}
	
	public static void adicionarMensagemDeInformacao(MensagemEnum mensagemEnum) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
				mensagemEnum.getDescricao(), null));
	}
	
	public static void redirecionarParaOutraPagina(String linkPagina, String mensagem) {
		try {
			FacesContext contextoAtual = FacesContext.getCurrentInstance();
			ExternalContext contextoExterno = contextoAtual.getExternalContext();
			Flash memoria = contextoExterno.getFlash();
			memoria.setKeepMessages(true);
			contextoAtual.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, null));
			contextoExterno.redirect(linkPagina);
		} catch (IOException excecao) {
			adicionarMensagemDeErro(MensagemErroEnum.ERRO_FAVOR_RETORNAR_PAGINA_INICIAL);
		}
	}
	
	public static String diaAtualEmFormatoDiaMesAno() {
		DateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		return formatoData.format(Calendar.getInstance(TimeZone.getDefault(), new Locale("pt", "BR")).getTime());
	}
	
	public static String horaMinutoAtualFormatado() {
		DateFormat formatoData = new SimpleDateFormat("HH:mm");
		return formatoData.format(Calendar.getInstance(TimeZone.getDefault(), new Locale("pt", "BR")).getTime());
	}
}