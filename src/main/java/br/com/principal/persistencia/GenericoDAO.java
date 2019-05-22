package br.com.principal.persistencia;

import javax.persistence.EntityManager;

import br.com.principal.conexao.FabricaConexao;

abstract class GenericoDAO {

	public Object salvar(Object entidade) {
		EntityManager em = new FabricaConexao().buscar();

		try {
			em.getTransaction().begin();
			em.persist(entidade);
			em.getTransaction().commit();
		} catch (Exception erro) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}

		return entidade;
	}

	public Object atualizar(Object entidade) {
		EntityManager entityManager = new FabricaConexao().buscar();

		try {
			entityManager.getTransaction().begin();
			entityManager.merge(entidade);
			entityManager.getTransaction().commit();
		} catch (Exception erro) {
			entityManager.getTransaction().rollback();
		} finally {
			entityManager.close();
		}

		return entidade;
	}
	
	public EntityManager obtemEntityManager() {
		return new FabricaConexao().buscar();
	}
}