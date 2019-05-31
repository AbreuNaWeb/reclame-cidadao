package br.com.principal.regra;

import java.util.List;

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
	
	public ReclamacaoSugestaoEntidade salvar(ReclamacaoSugestaoEntidade reclamacaoSugestaoEntidade) {
		if (possuiEndereco(reclamacaoSugestaoEntidade)) {
			enderecoRegras.salvar(reclamacaoSugestaoEntidade.getEndereco());
		}
		
		return (ReclamacaoSugestaoEntidade) reclamacaoSugestaoDAO.salvar(reclamacaoSugestaoEntidade);
	}
	
	public List<ReclamacaoSugestaoEntidade> buscarTodasReclamacoesSugestoes() {
		return reclamacaoSugestaoDAO.buscarTodasReclamacoesSugestoes();
	}
	
	public ReclamacaoSugestaoEntidade buscarPorID(Long id) {
		return reclamacaoSugestaoDAO.buscarPorID(id);
	}
	
	public boolean existeReclamacaoOuSugestaoAbertaVinculadaAoAgente(Long cpf) {
		return reclamacaoSugestaoDAO.existeReclamacaoOuSugestaoAbertaVinculadaAoAgente(cpf);
	}

	private boolean possuiEndereco(ReclamacaoSugestaoEntidade reclamacaoSugestaoEntidade) {
		return reclamacaoSugestaoEntidade.getEndereco() != null;
	}
}