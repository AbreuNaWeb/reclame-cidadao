package br.com.principal.persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.principal.entidade.ReclamacaoSugestaoEntidade;

public class ReclamacaoSugestaoDAO extends GenericoDAO {
	
	@SuppressWarnings("unchecked")
	public List<ReclamacaoSugestaoEntidade> buscarTodasReclamacoesSugestoes() {
		EntityManager em = obtemEntityManager();
		Query consulta = em.createNativeQuery("SELECT * FROM ReclamacaoSugestao", ReclamacaoSugestaoEntidade.class);
		return consulta.getResultList();
	}
}