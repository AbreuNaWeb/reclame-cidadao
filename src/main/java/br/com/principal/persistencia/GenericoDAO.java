package br.com.principal.persistencia;

import javax.persistence.EntityManager;

import br.com.principal.conexao.FabricaConexao;

abstract class GenericoDAO {

	Object salvar(Object entidade) {
		EntityManager em = new FabricaConexao().buscar();

		try {
			em.getTransaction().begin();
			em.persist(entidade);
		} catch (Exception erro) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}

		return entidade;
	}

	Object atualizar(Object entidade) {
		EntityManager em = new FabricaConexao().buscar();

		try {
			em.getTransaction().begin();
			em.merge(entidade);
		} catch (Exception erro) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}

		return entidade;
	}
	
	EntityManager obtemEntityManager() {
		return new FabricaConexao().buscar();
	}
}