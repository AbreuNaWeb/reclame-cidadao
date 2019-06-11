package br.com.principal.tela.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.principal.constante.TipoUsuarioEnum;
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
	
	public static boolean isNotAdministrador() {
		return !isAdministrador();
	}
	
	public static boolean isAdministrador() {
		return usuarioEstaLogado() && TipoUsuarioEnum.ADMINISTRADOR.igual(SessaoUtil.obterUsuarioLogado());
	}
	
	public static boolean isNotCidadao() {
		return !isCidadao();
	}

	public static boolean isCidadao() {
		return usuarioEstaLogado() && TipoUsuarioEnum.CIDADAO.igual(SessaoUtil.obterUsuarioLogado());
	}
	
	public static boolean isNotAgente() {
		return !isAgente();
	}

	public static boolean isAgente() {
		return usuarioEstaLogado() && TipoUsuarioEnum.AGENTE.igual(SessaoUtil.obterUsuarioLogado());
	}
}