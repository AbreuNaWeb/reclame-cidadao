package br.com.principal.tela.todos;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.principal.constante.TipoUsuarioEnum;
import br.com.principal.tela.util.SessaoUtil;

@Named
@RequestScoped
public class LayoutTela {
	
	public boolean usuarioNaoEstaLogado() {
		return !usuarioEstaLogado();
	}
	
	public boolean usuarioEstaLogado() {
		return SessaoUtil.usuarioEstaLogado();
	}
	
	public boolean isAdministrador() {
		return usuarioEstaLogado() && TipoUsuarioEnum.ADMINISTRADOR.igual(SessaoUtil.obterUsuarioLogado());
	}
	
	public boolean isCidadao() {
		return usuarioEstaLogado() && TipoUsuarioEnum.CIDADAO.igual(SessaoUtil.obterUsuarioLogado());
	}
	
	public boolean isAgente() {
		return usuarioEstaLogado() && TipoUsuarioEnum.AGENTE.igual(SessaoUtil.obterUsuarioLogado());
	}
}