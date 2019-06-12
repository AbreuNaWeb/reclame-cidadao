package br.com.principal.regra;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.principal.constante.CategoriasEnum;
import br.com.principal.constante.MensagemErroEnum;
import br.com.principal.constante.StatusReclamacaoSugestaoEnum;
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

	public void verificarSeExedeuLimites(UsuarioEntidade cidadao) throws RegraValidacaoException {
		List<ReclamacaoSugestaoEntidade> reclamacoesSugestoesAbertas = reclamacaoSugestaoDAO.buscarReclamacoesOuSugestoesAbertasDoCidadao(cidadao.getCpf());
		
		if (exedeuLimiteDeReclamacoesOuSugestoesAbertas(reclamacoesSugestoesAbertas)) {
			throw new RegraValidacaoException(MensagemErroEnum.ATINGIU_LIMITE_RECLAMACOES_SUGESTOES_ABERTAS);
		}
		
		List<ReclamacaoSugestaoEntidade> reclamacoesSugestoesCadastradasHoje = reclamacaoSugestaoDAO.buscarReclamacoesOuSugestoesCadastradasHoje(cidadao.getCpf());
	
		if (excedeuLimiteDeReclamacoesOuSugestoesCadastradasPorDia(reclamacoesSugestoesCadastradasHoje)) {
			throw new RegraValidacaoException(MensagemErroEnum.ATINGIU_LIMITE_RECLAMACOES_SUGESTOES_CADASTRADAS_DIA);
		}
	}
	
	public ReclamacaoSugestaoEntidade salvar(ReclamacaoSugestaoEntidade reclamacaoSugestaoEntidade, UsuarioEntidade usuarioEntidade, boolean informouEndereco) throws RegraValidacaoException {
		verificarEndereco(reclamacaoSugestaoEntidade, informouEndereco);
		reclamacaoSugestaoEntidade.setCidadao(usuarioEntidade);
		reclamacaoSugestaoEntidade.setHoraCriacao(TelaUtil.horaMinutoAtualFormatado());
		reclamacaoSugestaoEntidade.setHoraAtualizacao(TelaUtil.horaMinutoAtualFormatado());
		return (ReclamacaoSugestaoEntidade) reclamacaoSugestaoDAO.salvar(reclamacaoSugestaoEntidade);
	}
	
	////////////////////////////////////////
	
	public void atribuirReclamacaoOuSugestaoParaAgente(ReclamacaoSugestaoEntidade reclamacaoSugestaoEntidade, UsuarioEntidade agente) {
		reclamacaoSugestaoEntidade.setAgente(agente);
		reclamacaoSugestaoEntidade.setStatus(StatusReclamacaoSugestaoEnum.EM_ANALISE.getDescricao());
		reclamacaoSugestaoEntidade.setDataAtualizacao(TelaUtil.diaAtualEmFormatoDiaMesAno());
		reclamacaoSugestaoEntidade.setHoraAtualizacao(TelaUtil.horaMinutoAtualFormatado());
		reclamacaoSugestaoDAO.atualizar(reclamacaoSugestaoEntidade);
	}
	
	public void atualizar(ReclamacaoSugestaoEntidade reclamacaoSugestaoEntidade) {
		reclamacaoSugestaoEntidade.setDataAtualizacao(TelaUtil.diaAtualEmFormatoDiaMesAno());
		reclamacaoSugestaoEntidade.setHoraAtualizacao(TelaUtil.horaMinutoAtualFormatado());
		reclamacaoSugestaoDAO.atualizar(reclamacaoSugestaoEntidade);
	}
	
	public List<ReclamacaoSugestaoEntidade> buscarReclamacoesOuSugestoesDoCidadao(Long cpfDoCidadao) {
		return reclamacaoSugestaoDAO.buscarReclamacoesOuSugestoesDoCidadao(cpfDoCidadao);
	}
	
	private boolean exedeuLimiteDeReclamacoesOuSugestoesAbertas(List<ReclamacaoSugestaoEntidade> reclamacoesSugestoesAbertas) {
		return reclamacoesSugestoesAbertas.size() > 5;
	}

	private boolean excedeuLimiteDeReclamacoesOuSugestoesCadastradasPorDia(List<ReclamacaoSugestaoEntidade> reclamacoesSugestoesCadastradasHoje) {
		return reclamacoesSugestoesCadastradasHoje.size() > 1;
	}
	
	private void verificarEndereco(ReclamacaoSugestaoEntidade reclamacaoSugestaoEntidade, boolean informouEndereco) {
		if (informouEndereco) {
			reclamacaoSugestaoEntidade.setEndereco(enderecoRegras.salvar(reclamacaoSugestaoEntidade.getEndereco()));
		} else {
			reclamacaoSugestaoEntidade.setEndereco(null);
		}
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
	
	public List<ReclamacaoSugestaoEntidade> buscarReclamacoesOuSugestoesComFiltro(CategoriasEnum categoriaEnum, StatusReclamacaoSugestaoEnum statusEnum, Long cpfDoCidadao) {
		return reclamacaoSugestaoDAO.buscarReclamacoesOuSugestoesComFiltro(categoriaEnum, statusEnum, cpfDoCidadao);
	}
}