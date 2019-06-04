package br.com.principal.regra;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.principal.constante.MensagemEnum;
import br.com.principal.constante.TipoUsuarioEnum;
import br.com.principal.entidade.UsuarioEntidade;
import br.com.principal.excecao.RegraValidacaoException;
import br.com.principal.persistencia.UsuarioDAO;
import br.com.principal.tela.util.TelaUtil;

@Named
public class UsuarioRegras implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioDAO usuarioDAO;
	
	@Inject
	private ReclamacaoSugestaoRegras reclamacaoSugestaoRegras;

	public UsuarioEntidade realizarLogin(Long cpfInformado, String senhaInformada) throws RegraValidacaoException {
		UsuarioEntidade usuarioPesquisado = usuarioDAO.buscarPorCpfESenhaEmMD5(cpfInformado, converterSenhaParaMD5(senhaInformada));
		
		if (usuarioNaoEncontrado(usuarioPesquisado)) {
			throw new RegraValidacaoException(MensagemEnum.LOGIN_ERRADO);
		}
		
		return usuarioPesquisado;
	}
	
	public void salvarAgente(UsuarioEntidade novoAgente) throws RegraValidacaoException {
		UsuarioEntidade usuarioPesquisado = buscarPorCPF(novoAgente.getCpf());
		
		if (usuarioNaoEncontrado(usuarioPesquisado)) {
			novoAgente.setTipo(TipoUsuarioEnum.AGENTE.getDescricao());
			usuarioDAO.salvar(novoAgente);
		} else {
			throw new RegraValidacaoException(MensagemEnum.CPF_JA_CADASTRADO);
		}
	}
	
	public UsuarioEntidade buscarAgenteParaAtualizarOuExcluir(Long cpf) throws RegraValidacaoException {
		UsuarioEntidade usuario = usuarioDAO.buscarPorCPF(cpf);

		if (usuarioNaoEncontrado(usuario)) {
			throw new RegraValidacaoException(MensagemEnum.CPF_INEXISTENTE);
		}
		
		if (TipoUsuarioEnum.CIDADAO.igual(usuario) || TipoUsuarioEnum.ADMINISTRADOR.igual(usuario)) {
			throw new RegraValidacaoException(MensagemEnum.CPF_INFORMADO_NAO_PERTENCE_AGENTE);
		}
		
		if (reclamacaoSugestaoRegras.existeReclamacaoOuSugestaoAbertaVinculadaAoAgente(cpf)) {
			throw new RegraValidacaoException(MensagemEnum.RECLAMACAO_SUGESTAO_ABERTA_AGENTE);
		}
		
		return usuario;
	}
	
	public void salvarCidadao(UsuarioEntidade usuarioEntidade) {
		usuarioEntidade.setSenha(converterSenhaParaMD5(usuarioEntidade.getSenha()));
		usuarioEntidade.setTipo(TipoUsuarioEnum.CIDADAO.getDescricao());
		usuarioEntidade.setDataCadastro(TelaUtil.diaAtualEmFormatoDiaMesAno());
		usuarioDAO.salvar(usuarioEntidade);
	}
	
	public void excluir(UsuarioEntidade usuario) {
		usuarioDAO.excluir(usuario);
	}
	
	public void atualizar(UsuarioEntidade usuario) {
		usuarioDAO.atualizar(usuario);
	}
	
	public void atualizarComConversaoDeSenhaParaMD5(UsuarioEntidade usuarioEntidade) {
		usuarioEntidade.setSenha(converterSenhaParaMD5(usuarioEntidade.getSenha()));
		usuarioDAO.atualizar(usuarioEntidade);
	}
	
	public UsuarioEntidade buscarPorCPF(Long cpf) {
		return usuarioDAO.buscarPorCPF(cpf);
	}
	
	private boolean usuarioNaoEncontrado(UsuarioEntidade usuarioPesquisado) {
		return usuarioPesquisado == null;
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