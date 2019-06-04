package br.com.principal.tela.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.principal.entidade.UsuarioEntidade;

public class SessaoUtil {

	SessaoUtil() {
	}
	
	public static HttpSession obterSessao() {
		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}

	public static HttpServletRequest obterRequisicao() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}
	
	public static boolean usuarioEstaLogado() {
		return obterUsuarioLogado() != null;
	}

	public static UsuarioEntidade obterUsuarioLogado() {
		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		return (UsuarioEntidade) sessao.getAttribute("usuarioLogado");
	}
}