package br.com.principal.regra;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.principal.entidade.ReclamacaoSugestaoEntidade;
import br.com.principal.persistencia.ReclamacaoSugestaoDAO;

@Named
public class ReclamacaoSugestaoRegras {
	
	@Inject
	private ReclamacaoSugestaoDAO reclamacaoSugestaoDAO;
	
	public void salvar(ReclamacaoSugestaoEntidade reclamacaoSugestaoEntidade) {
		reclamacaoSugestaoDAO.salvar(reclamacaoSugestaoEntidade);
	}
}