package br.com.principal.conexao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FabricaConexao {
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("minhaPersistencia");
	
	public EntityManager buscar() {
		return emf.createEntityManager();
	}
}
