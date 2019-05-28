package br.com.principal.tela;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.principal.entidade.PessoaEntidade;

public class SessaoUtil {

	SessaoUtil() {
	}
	
	public static HttpSession obterSessao() {
		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}

	public static HttpServletRequest obterRequisicao() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}

	public static PessoaEntidade obterUsuarioLogado() {
		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		return (PessoaEntidade) sessao.getAttribute("usuarioLogado");
	}
}