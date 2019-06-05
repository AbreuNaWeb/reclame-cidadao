package br.com.principal.tela.cidadao;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.principal.constante.MensagemEnum;
import br.com.principal.entidade.UsuarioEntidade;
import br.com.principal.regra.UsuarioRegras;
import br.com.principal.tela.util.SessaoUtil;
import br.com.principal.tela.util.TelaUtil;

@Named
@ViewScoped
public class AlteraDadosPessoaisTela implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioRegras usuarioRegras;

	private UsuarioEntidade usuarioLogadoAtualizado;

	@PostConstruct
	public void inicializar() {
		UsuarioEntidade usuarioLogado = SessaoUtil.obterUsuarioLogado();
		this.usuarioLogadoAtualizado = usuarioRegras.buscarPorCPF(usuarioLogado.getCpf());
	}

	public void atualizar() {
		try {
			usuarioRegras.atualizarComConversaoDeSenhaParaMD5(usuarioLogadoAtualizado);
			TelaUtil.adicionarMensagemDeInformacao(MensagemEnum.DADOS_ATUALIZADOS_SUCESSO);
		} catch (Exception erroDesconhecido) {
			TelaUtil.adicionarMensagemDeErro(MensagemEnum.ERRO_DESCONHECIDO);
		}
	}

	public UsuarioEntidade getUsuarioLogadoAtualizado() {
		return usuarioLogadoAtualizado;
	}

	public void setUsuarioLogadoAtualizado(UsuarioEntidade usuarioLogadoAtualizado) {
		this.usuarioLogadoAtualizado = usuarioLogadoAtualizado;
	}
}