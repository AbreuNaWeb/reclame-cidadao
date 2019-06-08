package br.com.principal.tela.cidadao;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.principal.constante.CategoriasEnum;
import br.com.principal.constante.MensagemEnum;
import br.com.principal.constante.TipoReclamacaoSugestaoEnum;
import br.com.principal.constante.UnidadeFederativaEnum;
import br.com.principal.entidade.ReclamacaoSugestaoEntidade;
import br.com.principal.excecao.RegraValidacaoException;
import br.com.principal.regra.ReclamacaoSugestaoRegras;
import br.com.principal.tela.util.SessaoUtil;
import br.com.principal.tela.util.TelaUtil;

@Named
@ViewScoped
public class CadastraReclamacaoSugestaoTela implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ReclamacaoSugestaoRegras regra;

	private ReclamacaoSugestaoEntidade novaReclamacaoSugestao;
	
	private boolean informarEndereco;
	
	private boolean naoExedeuLimiteDePublicacoes;

	@PostConstruct
	public void inicializar() {
		this.novaReclamacaoSugestao = new ReclamacaoSugestaoEntidade();
	}
	
	public void verificarSeAtingiuLimite() {
		if (novaRequisicao()) {
			try {
				regra.verificarSeExedeuLimites(SessaoUtil.obterUsuarioLogado());
				this.naoExedeuLimiteDePublicacoes = true;
			} catch (RegraValidacaoException erroValidacao) {
				TelaUtil.adicionarMensagemDeErro(erroValidacao.getMensagemEnum());
				this.naoExedeuLimiteDePublicacoes = false;
			} catch (Exception erroDesconhecido) {
				TelaUtil.adicionarMensagemDeErro(MensagemEnum.ERRO_DESCONHECIDO);
				this.naoExedeuLimiteDePublicacoes = true;
			}
		}
	}

	public void cadastrar() {
		try {
			this.novaReclamacaoSugestao = regra.salvar(novaReclamacaoSugestao, SessaoUtil.obterUsuarioLogado(), informarEndereco);
			TelaUtil.redirecionarParaOutraPagina("/reclame-cidadao/detalheReclamacaoSugestao.xhtml?id=" + novaReclamacaoSugestao.getId(), MensagemEnum.CADASTROU_SUCESSO.getDescricao());
		} catch (RegraValidacaoException erroValidacao) {
			TelaUtil.adicionarMensagemDeErro(erroValidacao.getMensagemEnum());
		} catch (Exception erroDesconhecido) {
			TelaUtil.adicionarMensagemDeErro(MensagemEnum.ERRO_DESCONHECIDO);
		}
	}
	
	public TipoReclamacaoSugestaoEnum[] opcoesDeTipo() {
		return TipoReclamacaoSugestaoEnum.values();
	}

	public CategoriasEnum[] categorias() {
		return CategoriasEnum.values();
	}

	public UnidadeFederativaEnum[] unidadesFederativas() {
		return UnidadeFederativaEnum.values();
	}

	public ReclamacaoSugestaoEntidade getNovaReclamacaoSugestao() {
		return novaReclamacaoSugestao;
	}

	public void setNovaReclamacaoSugestao(ReclamacaoSugestaoEntidade novaReclamacaoSugestao) {
		this.novaReclamacaoSugestao = novaReclamacaoSugestao;
	}
	
	public boolean isInformarEndereco() {
		return informarEndereco;
	}
	
	public void setInformarEndereco(boolean informarEndereco) {
		this.informarEndereco = informarEndereco;
	}
	
	public boolean isNaoExedeuLimiteDePublicacoes() {
		return naoExedeuLimiteDePublicacoes;
	}
	
	private boolean novaRequisicao() {
		return !FacesContext.getCurrentInstance().isPostback();
	}
}