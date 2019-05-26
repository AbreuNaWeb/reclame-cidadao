package br.com.principal.tela;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.principal.entidade.ReclamacaoSugestaoEntidade;
import br.com.principal.regra.ReclamacaoSugestaoRegras;

@Named
@RequestScoped
public class ListaReclamacaoSugestao {

	@Inject
	private ReclamacaoSugestaoRegras regra;

	private List<ReclamacaoSugestaoEntidade> listaReclamacaoSugestaoEntidade;

	@PostConstruct
	public void inicializar() {
		this.listaReclamacaoSugestaoEntidade = regra.buscarTodasReclamacoesSugestoes();
	}

	public List<ReclamacaoSugestaoEntidade> getListaReclamacaoSugestaoEntidade() {
		return listaReclamacaoSugestaoEntidade;
	}
}