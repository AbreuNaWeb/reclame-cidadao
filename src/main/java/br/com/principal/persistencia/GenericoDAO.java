package br.com.principal.persistencia;

import javax.persistence.EntityManager;

import br.com.principal.conexao.FabricaConexao;

abstract class GenericoDAO {
	
	EntityManager gerenciadorDeEntidade;
	
	public Object salvar(Object entidade) {
		criarGerenciadorDeEntidadeSeNulo();

		try {
			gerenciadorDeEntidade.getTransaction().begin();
			gerenciadorDeEntidade.persist(entidade);
			gerenciadorDeEntidade.getTransaction().commit();
		} catch (Exception erro) {
			gerenciadorDeEntidade.getTransaction().rollback();
		}

		return entidade;
	}

	public Object atualizar(Object entidade) {
		criarGerenciadorDeEntidadeSeNulo();

		try {
			gerenciadorDeEntidade.getTransaction().begin();
			gerenciadorDeEntidade.merge(entidade);
			gerenciadorDeEntidade.getTransaction().commit();
		} catch (Exception erro) {
			gerenciadorDeEntidade.getTransaction().rollback();
		}

		return entidade;
	}
	
	public Object excluir(Object entidade) {
		criarGerenciadorDeEntidadeSeNulo();

		try {
			gerenciadorDeEntidade.getTransaction().begin();
			gerenciadorDeEntidade.remove(entidade);
			gerenciadorDeEntidade.flush();
			gerenciadorDeEntidade.getTransaction().commit();
		} catch (Exception erro) {
			gerenciadorDeEntidade.getTransaction().rollback();
		}
		
		return entidade;
	}
	
	public EntityManager obtemEntityManager() {
		criarGerenciadorDeEntidadeSeNulo();
		return gerenciadorDeEntidade;
	}
	
	private void criarGerenciadorDeEntidadeSeNulo() {
		this.gerenciadorDeEntidade = gerenciadorDeEntidade == null ? new FabricaConexao().buscar() : gerenciadorDeEntidade;
	}
}