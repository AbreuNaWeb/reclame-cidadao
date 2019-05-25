package br.com.principal.regra;

import java.util.Calendar;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.principal.entidade.ReclamacaoSugestaoEntidade;
import br.com.principal.persistencia.ReclamacaoSugestaoDAO;

@Named
public class ReclamacaoSugestaoRegras {
	
	@Inject
	private ReclamacaoSugestaoDAO reclamacaoSugestaoDAO;

	@Inject
	private EnderecoRegras enderecoRegras;
	
	public void salvar(ReclamacaoSugestaoEntidade reclamacaoSugestaoEntidade) {
		if (possuiEndereco(reclamacaoSugestaoEntidade)) {
			enderecoRegras.salvar(reclamacaoSugestaoEntidade.getEndereco());
		}
		
		reclamacaoSugestaoEntidade.setDataCriacao(Calendar.getInstance());
		reclamacaoSugestaoDAO.salvar(reclamacaoSugestaoEntidade);
	}

	private boolean possuiEndereco(ReclamacaoSugestaoEntidade reclamacaoSugestaoEntidade) {
		return reclamacaoSugestaoEntidade.getEndereco() != null;
	}
}