package br.com.principal.regra;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import br.com.principal.entidade.EnderecoEntidade;
import br.com.principal.excecao.RegraValidacaoException;
import br.com.principal.persistencia.EnderecoDAO;

@Named
public class EnderecoRegras {

	@Inject
	private EnderecoDAO enderecoDAO;

	public EnderecoEntidade salvar(EnderecoEntidade enderecoEntidade) throws RegraValidacaoException {
		if (naoInformouEstado(enderecoEntidade)) {
			return null;
		}
		
		return (EnderecoEntidade) enderecoDAO.salvar(enderecoEntidade);
	}

	private boolean naoInformouEstado(EnderecoEntidade enderecoEntidade) {
		return StringUtils.isBlank(enderecoEntidade.getEstado());
	}
}