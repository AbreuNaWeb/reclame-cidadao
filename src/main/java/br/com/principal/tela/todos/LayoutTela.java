package br.com.principal.tela.todos;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.principal.constante.MostrarNotificacaoEnum;
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
		return SessaoUtil.isAdministrador();
	}

	public boolean isCidadao() {
		return SessaoUtil.isCidadao();
	}

	public boolean isAgente() {
		return SessaoUtil.isAgente();
	}
	
	public boolean deveExibirNotificacao() {
		return SessaoUtil.usuarioEstaLogado() && MostrarNotificacaoEnum.SIM.igual(SessaoUtil.obterUsuarioLogado()); 
	}
	
	public void marcarParaNaoMostrarNotificacao() {
		usuarioRegras.marcarParaNaoMostrarNotificacao(SessaoUtil.obterUsuarioLogado());
	}
}