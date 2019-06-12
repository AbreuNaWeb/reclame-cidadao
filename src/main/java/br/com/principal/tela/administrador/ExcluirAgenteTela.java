package br.com.principal.tela.administrador;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.principal.constante.MensagemEnum;
import br.com.principal.constante.MensagemErroEnum;
import br.com.principal.entidade.UsuarioEntidade;
import br.com.principal.excecao.RegraValidacaoException;
import br.com.principal.regra.UsuarioRegras;
import br.com.principal.tela.util.SessaoUtil;
import br.com.principal.tela.util.TelaUtil;

@Named
@ViewScoped
public class ExcluirAgenteTela implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioRegras usuarioRegras;

	private UsuarioEntidade agenteParaExcluir;

	private Long cpfInformado;
	
	private boolean possuiPermissao;

	public void exibeMensagemCasoNaoPossuirPermissao() {
		this.possuiPermissao = SessaoUtil.isNotAdministrador() ? false : true;
		
		if (!possuiPermissao) {
			TelaUtil.adicionarMensagemDeErro(MensagemErroEnum.VOCE_NAO_TEM_PERMISSAO);
		}
	}
	
	public void pesquisarAgenteParaExcluir() {
		try {
			this.agenteParaExcluir = usuarioRegras.buscarAgenteParaAtualizarOuExcluir(cpfInformado);
		} catch (RegraValidacaoException erroValidacao) {
			this.agenteParaExcluir = null;
			TelaUtil.adicionarMensagemDeErro(erroValidacao.getMensagemErroEnum());
		} catch (Exception erroDesconhecido) {
			this.agenteParaExcluir = null;
			TelaUtil.adicionarMensagemDeErro(MensagemErroEnum.ERRO_DESCONHECIDO);
		}
	}

	public void excluir() {
		try {
			usuarioRegras.excluir(agenteParaExcluir);
			this.cpfInformado = null;
			this.agenteParaExcluir = null;
			TelaUtil.adicionarMensagemDeInformacao(MensagemEnum.AGENTE_EXCLUIDO_SUCESSO);
		} catch (Exception erroDesconhecido) {
			TelaUtil.adicionarMensagemDeErro(MensagemErroEnum.ERRO_DESCONHECIDO);
		}
	}

	public Long getCpfInformado() {
		return cpfInformado;
	}

	public void setCpfInformado(Long cpfInformado) {
		this.cpfInformado = cpfInformado;
	}

	public UsuarioEntidade getAgenteParaExcluir() {
		return agenteParaExcluir;
	}
	
	public boolean isPossuiPermissao() {
		return possuiPermissao;
	}
}