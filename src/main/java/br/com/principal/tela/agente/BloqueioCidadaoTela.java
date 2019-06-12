package br.com.principal.tela.agente;

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
public class BloqueioCidadaoTela implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioRegras usuarioRegras;

	private UsuarioEntidade cidadaoParaBloquear;

	private Long cpfInformado;
	
	private boolean possuiPermissao;

	public void exibeMensagemCasoNaoPossuirPermissao() {
		this.possuiPermissao = SessaoUtil.isNotAgente() ? false : true;
		
		if (!possuiPermissao) {
			TelaUtil.adicionarMensagemDeErro(MensagemErroEnum.VOCE_NAO_TEM_PERMISSAO);
		}
	}
	
	public void pesquisarCidadaoParaBloquear() {
		try {
			this.cidadaoParaBloquear = usuarioRegras.buscarCidadaoParaBloquear(cpfInformado);
		} catch (RegraValidacaoException erroValidacao) {
			this.cidadaoParaBloquear = null;
			TelaUtil.adicionarMensagemDeErro(erroValidacao.getMensagemErroEnum());
		} catch (Exception erroDesconhecido) {
			this.cidadaoParaBloquear = null;
			TelaUtil.adicionarMensagemDeErro(MensagemErroEnum.ERRO_DESCONHECIDO);
		}
	}
	
	public void bloquear() {
		try {
			usuarioRegras.bloquear(cidadaoParaBloquear);
			this.cpfInformado = null;
			this.cidadaoParaBloquear = null;
			TelaUtil.adicionarMensagemDeInformacao(MensagemEnum.CIDADAO_BLOQUEADO_SUCESSO);
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
	
	public UsuarioEntidade getCidadaoParaBloquear() {
		return cidadaoParaBloquear;
	}
	
	public boolean isPossuiPermissao() {
		return possuiPermissao;
	}
}