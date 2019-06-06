package br.com.principal.regra;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.principal.constante.MensagemEnum;
import br.com.principal.entidade.ReclamacaoSugestaoEntidade;
import br.com.principal.entidade.UsuarioEntidade;
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

	public ReclamacaoSugestaoEntidade salvar(ReclamacaoSugestaoEntidade reclamacaoSugestaoEntidade, UsuarioEntidade usuarioEntidade, boolean informouEndereco) throws RegraValidacaoException {
		verificarEndereco(reclamacaoSugestaoEntidade, informouEndereco);
		reclamacaoSugestaoEntidade.setCidadao(usuarioEntidade);
		reclamacaoSugestaoEntidade.setHoraCriacao(TelaUtil.converterCalendarParaHoraMinuto(Calendar.getInstance()));
		return (ReclamacaoSugestaoEntidade) reclamacaoSugestaoDAO.salvar(reclamacaoSugestaoEntidade);
	}

	public void verificarSeExedeuLimites(UsuarioEntidade usuarioEntidade) throws RegraValidacaoException {
		List<ReclamacaoSugestaoEntidade> reclamacoesSugestoesAbertas = reclamacaoSugestaoDAO.buscarReclamacoesOuSugestoesAbertasDoCidadao(usuarioEntidade.getCpf());
		
		if (exedeuLimiteDeReclamacoesOuSugestoesAbertas(reclamacoesSugestoesAbertas)) {
			throw new RegraValidacaoException(MensagemEnum.ATINGIU_LIMITE_RECLAMACOES_SUGESTOES_ABERTAS);
		}
		
		List<ReclamacaoSugestaoEntidade> reclamacoesSugestoesCadastradasHoje = reclamacaoSugestaoDAO.buscarReclamacoesOuSugestoesCadastradasHoje(usuarioEntidade.getCpf());
	
		if (excedeuLimiteDeReclamacoesOuSugestoesCadastradasPorDia(reclamacoesSugestoesCadastradasHoje)) {
			throw new RegraValidacaoException(MensagemEnum.ATINGIU_LIMITE_RECLAMACOES_SUGESTOES_CADASTRADAS_DIA);
		}
	}

	private boolean excedeuLimiteDeReclamacoesOuSugestoesCadastradasPorDia(List<ReclamacaoSugestaoEntidade> reclamacoesSugestoesCadastradasHoje) {
		return reclamacoesSugestoesCadastradasHoje.size() > 1;
	}

	private boolean exedeuLimiteDeReclamacoesOuSugestoesAbertas(List<ReclamacaoSugestaoEntidade> reclamacoesSugestoesAbertas) {
		return reclamacoesSugestoesAbertas.size() > 5;
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

	private void verificarEndereco(ReclamacaoSugestaoEntidade reclamacaoSugestaoEntidade, boolean informouEndereco) {
		if (informouEndereco) {
			reclamacaoSugestaoEntidade.setEndereco(enderecoRegras.salvar(reclamacaoSugestaoEntidade.getEndereco()));
		} else {
			reclamacaoSugestaoEntidade.setEndereco(null);
		}
	}
}