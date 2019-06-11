package br.com.principal.tela.todos;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.principal.constante.CategoriasEnum;
import br.com.principal.constante.StatusReclamacaoSugestaoEnum;
import br.com.principal.entidade.ReclamacaoSugestaoEntidade;
import br.com.principal.regra.ReclamacaoSugestaoRegras;

@Named
@RequestScoped
public class ListaReclamacaoSugestaoTela {

	@Inject
	private ReclamacaoSugestaoRegras regra;

	private List<ReclamacaoSugestaoEntidade> listaReclamacaoSugestaoEntidade;

	private CategoriasEnum categoriaParaPesquisar;

	private StatusReclamacaoSugestaoEnum statusParaPesquisar;

	@PostConstruct
	public void inicializar() {
		this.listaReclamacaoSugestaoEntidade = regra.buscarTodasReclamacoesSugestoes();
	}

	public void pesquisar() {
		this.listaReclamacaoSugestaoEntidade = regra.buscarReclamacoesOuSugestoesComFiltro(categoriaParaPesquisar, statusParaPesquisar, null);
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
}