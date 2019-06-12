package br.com.principal.tela.cidadao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.principal.constante.CategoriasEnum;
import br.com.principal.constante.MensagemErroEnum;
import br.com.principal.constante.StatusReclamacaoSugestaoEnum;
import br.com.principal.entidade.ReclamacaoSugestaoEntidade;
import br.com.principal.regra.ReclamacaoSugestaoRegras;
import br.com.principal.tela.util.SessaoUtil;
import br.com.principal.tela.util.TelaUtil;

@Named
@RequestScoped
public class MinhasReclamacoesSugestoesTela {

	@Inject
	private ReclamacaoSugestaoRegras regra;

	private List<ReclamacaoSugestaoEntidade> listaReclamacaoSugestaoEntidade;

	private CategoriasEnum categoriaParaPesquisar;

	private StatusReclamacaoSugestaoEnum statusParaPesquisar;
	
	private boolean possuiPermissao;

	@PostConstruct
	public void inicializar() {
		this.listaReclamacaoSugestaoEntidade = regra.buscarReclamacoesOuSugestoesDoCidadao(SessaoUtil.obterUsuarioLogado().getCpf());
	}

	public void pesquisar() {
		this.listaReclamacaoSugestaoEntidade = regra.buscarReclamacoesOuSugestoesComFiltro(categoriaParaPesquisar, statusParaPesquisar, SessaoUtil.obterUsuarioLogado().getCpf());
	}
	
	public void exibeMensagemCasoNaoPossuirPermissao() {
		this.possuiPermissao = SessaoUtil.isNotCidadao() ? false : true;
		
		if (!possuiPermissao) {
			TelaUtil.adicionarMensagemDeErro(MensagemErroEnum.VOCE_NAO_TEM_PERMISSAO);
		}
	}

	public List<ReclamacaoSugestaoEntidade> getListaReclamacaoSugestaoEntidade() {
		return listaReclamacaoSugestaoEntidade;
	}

	public CategoriasEnum[] getCategorias() {
		return CategoriasEnum.values();
	}

	public StatusReclamacaoSugestaoEnum[] getStatus() {
		return StatusReclamacaoSugestaoEnum.values();
	}

	public CategoriasEnum getCategoriaParaPesquisar() {
		return categoriaParaPesquisar;
	}

	public void setCategoriaParaPesquisar(CategoriasEnum categoriaParaPesquisar) {
		this.categoriaParaPesquisar = categoriaParaPesquisar;
	}

	public StatusReclamacaoSugestaoEnum getStatusParaPesquisar() {
		return statusParaPesquisar;
	}

	public void setStatusParaPesquisar(StatusReclamacaoSugestaoEnum statusParaPesquisar) {
		this.statusParaPesquisar = statusParaPesquisar;
	}
	
	public boolean isPossuiPermissao() {
		return possuiPermissao;
	}
}