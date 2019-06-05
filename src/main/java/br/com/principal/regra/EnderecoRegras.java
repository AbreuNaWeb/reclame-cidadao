package br.com.principal.regra;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.principal.entidade.EnderecoEntidade;
import br.com.principal.persistencia.EnderecoDAO;

@Named
public class EnderecoRegras {

	@Inject
	private EnderecoDAO enderecoDAO;

	public EnderecoEntidade salvar(EnderecoEntidade enderecoEntidade) {
		return (EnderecoEntidade) enderecoDAO.salvar(enderecoEntidade);
	}
}