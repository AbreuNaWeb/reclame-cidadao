package br.com.principal.regra;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.principal.entidade.PessoaEntidade;
import br.com.principal.persistencia.PessoaDAO;

@Named
public class PessoaRegras {

	@Inject
	private PessoaDAO pessoaDAO;

	public void salvar(PessoaEntidade pessoaEntidade) {
		pessoaDAO.salvar(pessoaEntidade);
	}
	
	public void atualizar(PessoaEntidade pessoaEntidade) {
		pessoaDAO.atualizar(pessoaEntidade);
	}
	
	public PessoaEntidade buscarPorCPF(Long cpf) {
		return pessoaDAO.buscarPorCPF(cpf);
	}
}
