package br.com.principal.regra;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.principal.entidade.PessoaEntidade;
import br.com.principal.persistencia.PessoaDAO;

@Named
public class PessoaRegras implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private PessoaDAO pessoaDAO;

	public void salvar(PessoaEntidade pessoaEntidade) {
		pessoaEntidade.setSenha(converterSenhaParaMD5(pessoaEntidade.getSenha()));
		pessoaDAO.salvar(pessoaEntidade);
	}
	
	public void atualizar(PessoaEntidade pessoaEntidade) {
		pessoaDAO.atualizar(pessoaEntidade);
	}
	
	public PessoaEntidade buscarPorCPF(Long cpf) {
		return pessoaDAO.buscarPorCPF(cpf);
	}
	
	public PessoaEntidade buscarPorCpfESenhaEmMD5(Long cpf, String senha) {
		return pessoaDAO.buscarPorCpfESenhaEmMD5(cpf, converterSenhaParaMD5(senha));
	}
	
	private String converterSenhaParaMD5(String senha) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			byte[] valorMD5 = messageDigest.digest(senha.getBytes("UTF-8"));
			StringBuilder stringBuilder = new StringBuilder();
			
			for (byte item : valorMD5) {
				stringBuilder.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
			}

			return stringBuilder.toString();

		} catch (NoSuchAlgorithmException | UnsupportedEncodingException excecao) {
			return null;
		}
	}
}