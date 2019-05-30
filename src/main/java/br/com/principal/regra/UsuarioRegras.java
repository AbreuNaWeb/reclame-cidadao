package br.com.principal.regra;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.principal.entidade.UsuarioEntidade;
import br.com.principal.persistencia.UsuarioDAO;

@Named
public class UsuarioRegras implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioDAO usuarioDAO;

	public void salvar(UsuarioEntidade usuarioEntidade) {
		usuarioEntidade.setSenha(converterSenhaParaMD5(usuarioEntidade.getSenha()));
		usuarioDAO.salvar(usuarioEntidade);
	}
	
	public void atualizar(UsuarioEntidade usuarioEntidade) {
		usuarioDAO.atualizar(usuarioEntidade);
	}
	
	public UsuarioEntidade buscarPorCPF(Long cpf) {
		return usuarioDAO.buscarPorCPF(cpf);
	}
	
	public UsuarioEntidade buscarPorCpfESenhaEmMD5(Long cpf, String senha) {
		return usuarioDAO.buscarPorCpfESenhaEmMD5(cpf, converterSenhaParaMD5(senha));
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