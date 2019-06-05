package br.com.principal.regra;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.principal.entidade.ReclamacaoSugestaoEntidade;
import br.com.principal.excecao.RegraValidacaoException;
import br.com.principal.persistencia.ReclamacaoSugestaoDAO;
import br.com.principal.tela.util.TelaUtil;

@Named
public class ReclamacaoSugestaoRegras implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ReclamacaoSugestaoDAO reclamacaoSugestaoDAO;

	@Inject
	private EnderecoRegras enderecoRegras;

	public ReclamacaoSugestaoEntidade salvar(ReclamacaoSugestaoEntidade reclamacaoSugestaoEntidade) throws RegraValidacaoException {
		reclamacaoSugestaoEntidade.setEndereco(enderecoRegras.salvar(reclamacaoSugestaoEntidade.getEndereco()));
		reclamacaoSugestaoEntidade.setHoraCriacao(TelaUtil.converterCalendarParaHoraMinuto(Calendar.getInstance()));
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
}