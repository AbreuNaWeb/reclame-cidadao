package br.com.principal.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.principal.entidade.UsuarioEntidade;

public class UsuarioDAO extends GenericoDAO {
	
	public UsuarioEntidade buscarPorCPF(Long cpf) {
		EntityManager em = obtemEntityManager();
		StringBuilder sql = new StringBuilder("SELECT * FROM Usuario WHERE CPF = :CPF");
		Query consulta = em.createNativeQuery(sql.toString(), UsuarioEntidade.class);
		consulta.setParameter("CPF", cpf);
		
		try {
			return (UsuarioEntidade) consulta.getSingleResult();
		} catch (NoResultException excecao) {
			return null;
		}
	}
	
	public UsuarioEntidade buscarPorCpfESenhaEmMD5(Long cpf, String senhaEmMD5) {
		EntityManager em = obtemEntityManager();
		StringBuilder sql = new StringBuilder("SELECT * FROM Usuario WHERE CPF = :CPF AND SENHA = :SENHA");
		Query consulta = em.createNativeQuery(sql.toString(), UsuarioEntidade.class);
		consulta.setParameter("CPF", cpf);
		consulta.setParameter("SENHA", senhaEmMD5);
		
		try {
			return (UsuarioEntidade) consulta.getSingleResult();
		} catch (NoResultException excecao) {
			return null;
		}
	}
}