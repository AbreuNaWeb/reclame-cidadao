package br.com.principal.persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

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
}