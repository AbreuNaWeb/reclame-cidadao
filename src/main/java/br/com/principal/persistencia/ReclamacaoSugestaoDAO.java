package br.com.principal.persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.principal.constante.CategoriasEnum;
import br.com.principal.constante.StatusReclamacaoSugestaoEnum;
import br.com.principal.entidade.ReclamacaoSugestaoEntidade;
import br.com.principal.tela.util.TelaUtil;

public class ReclamacaoSugestaoDAO extends GenericoDAO {
	
	@SuppressWarnings("unchecked")
	public List<ReclamacaoSugestaoEntidade> buscarTodasReclamacoesSugestoes() {
		EntityManager em = obtemEntityManager();
		StringBuilder sql = new StringBuilder("SELECT * FROM ReclamacaoSugestao"); 
		Query consulta = em.createNativeQuery(sql.toString(), ReclamacaoSugestaoEntidade.class);
		return consulta.getResultList();
	}
	
	public ReclamacaoSugestaoEntidade buscarPorID(Long id) {
		EntityManager em = obtemEntityManager();
		StringBuilder sql = new StringBuilder("SELECT * FROM ReclamacaoSugestao WHERE ID = :ID");
		Query consulta = em.createNativeQuery(sql.toString(), ReclamacaoSugestaoEntidade.class);
		consulta.setParameter("ID", id);
		
		try {
			return (ReclamacaoSugestaoEntidade) consulta.getSingleResult();
		} catch (NoResultException excecao) {
			return null;
		}
	}
	
	public boolean existeReclamacaoOuSugestaoAbertaVinculadaAoAgente(Long cpf) {
		EntityManager em = obtemEntityManager();
		StringBuilder sql = new StringBuilder("SELECT * FROM ReclamacaoSugestao WHERE ");
		sql.append("CPF_AGENTE = :CPF AND ");
		sql.append("STATUS = :STATUS_ABERTO");
		Query consulta = em.createNativeQuery(sql.toString(), ReclamacaoSugestaoEntidade.class);
		consulta.setParameter("CPF", cpf);
		consulta.setParameter("STATUS_ABERTO", StatusReclamacaoSugestaoEnum.ABERTA.getDescricao());
		consulta.setMaxResults(1);
		return !consulta.getResultList().isEmpty();
	}
	
	@SuppressWarnings("unchecked")
	public List<ReclamacaoSugestaoEntidade> buscarReclamacoesOuSugestoesAbertasDoCidadao(Long cpfDoCidadao) {
		EntityManager em = obtemEntityManager();
		StringBuilder sql = new StringBuilder("SELECT * FROM ReclamacaoSugestao WHERE ");
		sql.append("CPF_CIDADAO = :CPF_CIDADAO AND ");
		sql.append("STATUS = :STATUS_ABERTO");
		Query consulta = em.createNativeQuery(sql.toString(), ReclamacaoSugestaoEntidade.class);
		consulta.setParameter("CPF_CIDADAO", cpfDoCidadao);
		consulta.setParameter("STATUS_ABERTO", StatusReclamacaoSugestaoEnum.ABERTA.getDescricao());
		return consulta.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<ReclamacaoSugestaoEntidade> buscarReclamacoesOuSugestoesDoCidadao(Long cpfDoCidadao) {
		EntityManager em = obtemEntityManager();
		StringBuilder sql = new StringBuilder("SELECT * FROM ReclamacaoSugestao WHERE ");
		sql.append("CPF_CIDADAO = :CPF_CIDADAO");
		Query consulta = em.createNativeQuery(sql.toString(), ReclamacaoSugestaoEntidade.class);
		consulta.setParameter("CPF_CIDADAO", cpfDoCidadao);
		return consulta.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<ReclamacaoSugestaoEntidade> buscarReclamacoesOuSugestoesCadastradasHoje(Long cpfDoCidadao) {
		EntityManager em = obtemEntityManager();
		StringBuilder sql = new StringBuilder("SELECT * FROM ReclamacaoSugestao WHERE ");
		sql.append("CPF_CIDADAO = :CPF_CIDADAO AND ");
		sql.append("DATA_CRIACAO = :DATA_CRIACAO");
		Query consulta = em.createNativeQuery(sql.toString(), ReclamacaoSugestaoEntidade.class);
		consulta.setParameter("CPF_CIDADAO", cpfDoCidadao);
		consulta.setParameter("DATA_CRIACAO", TelaUtil.diaAtualEmFormatoDiaMesAno());
		return consulta.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<ReclamacaoSugestaoEntidade> buscarReclamacoesOuSugestoesComFiltro(CategoriasEnum categoriaEnum, StatusReclamacaoSugestaoEnum statusEnum, Long cpfDoCidadao) {
		EntityManager em = obtemEntityManager();
		StringBuilder sql = new StringBuilder("SELECT * FROM ReclamacaoSugestao WHERE ");
		sql.append("CATEGORIA = :CATEGORIA AND ");
		sql.append("STATUS = :STATUS ");
		
		if (cpfDoCidadao != null) {
			sql.append("AND CPF_CIDADAO = :CPF_CIDADAO");
		}
		
		sql.append("ORDER BY DATA_ATUALIZACAO DESC");
		
		Query consulta = em.createNativeQuery(sql.toString(), ReclamacaoSugestaoEntidade.class);
		consulta.setParameter("CATEGORIA", categoriaEnum.getDescricao());
		consulta.setParameter("STATUS", statusEnum.getDescricao());
		
		if (cpfDoCidadao != null) {
			consulta.setParameter("CPF_CIDADAO", cpfDoCidadao);
		}
		
		return consulta.getResultList();
	}
}