package br.com.principal.tela.todos;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.principal.constante.MostrarNotificacaoEnum;
import br.com.principal.constante.TipoUsuarioEnum;
import br.com.principal.regra.UsuarioRegras;
import br.com.principal.tela.util.SessaoUtil;

@Named
@RequestScoped
public class LayoutTela {

	@Inject
	private UsuarioRegras usuarioRegras;

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
	
	public boolean deveExibirNotificacao() {
		return SessaoUtil.usuarioEstaLogado() && MostrarNotificacaoEnum.SIM.igual(SessaoUtil.obterUsuarioLogado()); 
	}
	
	public void marcarParaNaoMostrarNotificacao() {
		usuarioRegras.marcarParaNaoMostrarNotificacao(SessaoUtil.obterUsuarioLogado());
	}
}