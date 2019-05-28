package br.com.principal.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.principal.entidade.PessoaEntidade;

public class PessoaDAO extends GenericoDAO {
	
	public PessoaEntidade buscarPorCPF(Long cpf) {
		EntityManager em = obtemEntityManager();
		StringBuilder sql = new StringBuilder("SELECT * FROM Pessoa WHERE CPF = :CPF");
		Query consulta = em.createNativeQuery(sql.toString(), PessoaEntidade.class);
		consulta.setParameter("CPF", cpf);
		
		try {
			return (PessoaEntidade) consulta.getSingleResult();
		} catch (NoResultException excecao) {
			return null;
		}
	}
	
	public PessoaEntidade buscarPorCpfESenhaEmMD5(Long cpf, String senhaEmMD5) {
		EntityManager em = obtemEntityManager();
		StringBuilder sql = new StringBuilder("SELECT * FROM Pessoa WHERE CPF = :CPF AND SENHA = :SENHA");
		Query consulta = em.createNativeQuery(sql.toString(), PessoaEntidade.class);
		consulta.setParameter("CPF", cpf);
		consulta.setParameter("SENHA", senhaEmMD5);
		
		try {
			return (PessoaEntidade) consulta.getSingleResult();
		} catch (NoResultException excecao) {
			return null;
		}
	}
}