package br.com.principal.persistencia;

import javax.persistence.EntityManager;

import br.com.principal.conexao.FabricaConexao;
import br.com.principal.modelo.Notificacao;

public class NotificacaoDAO {

	public Notificacao salvar(Notificacao notificacao) {
		EntityManager em = new FabricaConexao().buscar();

		try {
			em.getTransaction().begin();
			em.persist(notificacao);
		} catch (Exception erro) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}

		return notificacao;
	}
	
	public Notificacao atualizar(Notificacao notificacao) {
		EntityManager em = new FabricaConexao().buscar();

		try {
			em.getTransaction().begin();
			em.merge(notificacao);
		} catch (Exception erro) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}

		return notificacao;
	}
	
	public Notificacao buscarPorID(Long id) {
		EntityManager em = new FabricaConexao().buscar();
		Notificacao notificacao = null;
		
		try {
			notificacao = em.find(Notificacao.class, id);
		} catch (Exception erro) {
		} finally {
			em.close();
		}

		return notificacao;
	}
}