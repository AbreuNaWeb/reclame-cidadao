package br.com.principal.persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.principal.constante.StatusEnum;
import br.com.principal.entidade.ReclamacaoSugestaoEntidade;

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
		consulta.setParameter("STATUS_ABERTO", StatusEnum.ABERTA.getDescricao());
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
		consulta.setParameter("STATUS_ABERTO", StatusEnum.ABERTA.getDescricao());
		return consulta.getResultList();
	}
}