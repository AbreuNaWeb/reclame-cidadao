package br.com.principal.tela.administrador;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.principal.constante.CategoriasEnum;
import br.com.principal.constante.MensagemEnum;
import br.com.principal.constante.MensagemErroEnum;
import br.com.principal.entidade.UsuarioEntidade;
import br.com.principal.excecao.RegraValidacaoException;
import br.com.principal.regra.UsuarioRegras;
import br.com.principal.tela.util.TelaUtil;

@Named
@ViewScoped
public class AtualizaAgenteTela implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioRegras usuarioRegras;

	private UsuarioEntidade agenteParaAtualizar;

	private Long cpfInformado;

	public void pesquisarAgenteParaAtualizar() {
		try {
			this.agenteParaAtualizar = usuarioRegras.buscarAgenteParaAtualizarOuExcluir(cpfInformado);
		} catch (RegraValidacaoException erroValidacao) {
			this.agenteParaAtualizar = null;
			TelaUtil.adicionarMensagemDeErro(erroValidacao.getMensagemErroEnum());
		} catch (Exception erroDesconhecido) {
			this.agenteParaAtualizar = null;
			TelaUtil.adicionarMensagemDeErro(MensagemErroEnum.ERRO_DESCONHECIDO);
		}
	}

	public void atualizar() {
		try {
			usuarioRegras.atualizar(agenteParaAtualizar);
			this.cpfInformado = null;
			this.agenteParaAtualizar = null;
			TelaUtil.adicionarMensagemDeInformacao(MensagemEnum.AGENTE_ATUALIZADO_SUCESSO);
		} catch (Exception erroDesconhecido) {
			TelaUtil.adicionarMensagemDeErro(MensagemErroEnum.ERRO_DESCONHECIDO);
		}
	}

	public CategoriasEnum[] categorias() {
		return CategoriasEnum.values();
	}

	public UsuarioEntidade getAgenteParaAtualizar() {
		return agenteParaAtualizar;
	}

	public Long getCpfInformado() {
		return cpfInformado;
	}

	public void setCpfInformado(Long cpfInformado) {
		this.cpfInformado = cpfInformado;
	}
}