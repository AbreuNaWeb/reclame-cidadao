package br.com.principal.regra;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.principal.entidade.EnderecoEntidade;
import br.com.principal.persistencia.EnderecoDAO;

@Named
public class EnderecoRegras {
	
	@Inject
	private EnderecoDAO enderecoDAO;
	
	public void salvar(EnderecoEntidade enderecoEntidade) {
		enderecoDAO.salvar(enderecoEntidade);
	}
}