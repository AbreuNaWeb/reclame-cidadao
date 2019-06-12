package br.com.principal.regra;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.principal.constante.MensagemErroEnum;
import br.com.principal.constante.MostrarNotificacaoEnum;
import br.com.principal.constante.StatusUsuarioEnum;
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
	
	public void cadastrarUsuario(UsuarioEntidade novoUsuario) throws RegraValidacaoException {
		UsuarioEntidade usuarioPesquisado = buscarPorCPF(novoUsuario);
		
		if (usuarioNaoEncontrado(usuarioPesquisado)) {
			salvarCidadao(novoUsuario);
		} else if (agenteAindaNaoSeCadastrou(usuarioPesquisado)) {
			atualizarDadosDoAgente(usuarioPesquisado, novoUsuario);
		} else {
			throw new RegraValidacaoException(MensagemErroEnum.CPF_JA_CADASTRADO);
		}
	}
	
	public UsuarioEntidade buscarPorCPF(UsuarioEntidade usuarioEntidade) {
		return usuarioDAO.buscarPorCPF(usuarioEntidade.getCpf());
	}
	
	public UsuarioEntidade realizarLogin(Long cpfInformado, String senhaInformada) throws RegraValidacaoException {
		UsuarioEntidade usuarioPesquisado = usuarioDAO.buscarPorCpfESenhaEmMD5(cpfInformado, converterSenhaParaMD5(senhaInformada));

		if (usuarioNaoEncontrado(usuarioPesquisado)) {
			throw new RegraValidacaoException(MensagemErroEnum.LOGIN_ERRADO);
		}
		
		if (StatusUsuarioEnum.BLOQUEADO.igual(usuarioPesquisado)) {
			throw new RegraValidacaoException(MensagemErroEnum.VOCE_FOI_BLOQUEADO);
		}

		return usuarioPesquisado;
	}
	
	public void atualizarComConversaoDeSenhaParaMD5(UsuarioEntidade usuarioEntidade) {
		usuarioEntidade.setSenha(converterSenhaParaMD5(usuarioEntidade.getSenha()));
		usuarioDAO.atualizar(usuarioEntidade);
	}
	
	public UsuarioEntidade buscarCidadaoParaBloquear(Long cpf) throws RegraValidacaoException {
		UsuarioEntidade usuario = buscarUsuario(cpf);

		if (TipoUsuarioEnum.AGENTE.igual(usuario) || TipoUsuarioEnum.ADMINISTRADOR.igual(usuario)) {
			throw new RegraValidacaoException(MensagemErroEnum.CPF_INFORMADO_NAO_PERTENCE_CIDADAO);
		}
		
		if (StatusUsuarioEnum.BLOQUEADO.igual(usuario)) {
			throw new RegraValidacaoException(MensagemErroEnum.CIDADAO_JA_BLOQUEADO);
		}
		
		return usuario;
	}
	
	public void bloquear(UsuarioEntidade usuario) {
		usuario.setStatus(StatusUsuarioEnum.BLOQUEADO.getDescricao());
		usuarioDAO.atualizar(usuario);
	}
	
	public void marcarParaMostrarNotificacao(UsuarioEntidade usuarioEntidade) {
		usuarioEntidade.setMostrarNotificacao(MostrarNotificacaoEnum.SIM.getDescricao());
		atualizar(usuarioEntidade);
	}
	
	public void marcarParaNaoMostrarNotificacao(UsuarioEntidade usuarioEntidade) {
		usuarioEntidade.setMostrarNotificacao(MostrarNotificacaoEnum.NAO.getDescricao());
		atualizar(usuarioEntidade);
	}

	public void salvarAgente(UsuarioEntidade novoAgente) throws RegraValidacaoException {
		UsuarioEntidade usuarioPesquisado = buscarPorCPF(novoAgente);

		if (usuarioNaoEncontrado(usuarioPesquisado)) {
			novoAgente.setTipo(TipoUsuarioEnum.AGENTE.getDescricao());
			usuarioDAO.salvar(novoAgente);
		} else {
			throw new RegraValidacaoException(MensagemErroEnum.CPF_JA_CADASTRADO);
		}
	}

	public UsuarioEntidade buscarAgenteParaAtualizarOuExcluir(Long cpf) throws RegraValidacaoException {
		UsuarioEntidade usuario = buscarUsuario(cpf);

		if (TipoUsuarioEnum.CIDADAO.igual(usuario) || TipoUsuarioEnum.ADMINISTRADOR.igual(usuario)) {
			throw new RegraValidacaoException(MensagemErroEnum.CPF_INFORMADO_NAO_PERTENCE_AGENTE);
		}

		if (reclamacaoSugestaoRegras.existeReclamacaoOuSugestaoAbertaVinculadaAoAgente(cpf)) {
			throw new RegraValidacaoException(MensagemErroEnum.RECLAMACAO_SUGESTAO_ABERTA_AGENTE);
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
	
	private boolean agenteAindaNaoSeCadastrou(UsuarioEntidade usuarioPesquisado) {
		return TipoUsuarioEnum.AGENTE.igual(usuarioPesquisado) && usuarioPesquisado.getDataCadastro() == null;
	}
	
	private void atualizarDadosDoAgente(UsuarioEntidade usuarioPesquisado, UsuarioEntidade agente) {
		usuarioPesquisado.setNome(agente.getNome());
		usuarioPesquisado.setEmail(agente.getEmail());
		usuarioPesquisado.setSenha(agente.getSenha());
		usuarioPesquisado.setDddCelular(agente.getDddCelular());
		usuarioPesquisado.setDataCadastro(TelaUtil.diaAtualEmFormatoDiaMesAno());
		atualizarComConversaoDeSenhaParaMD5(usuarioPesquisado);
	}
	
	private UsuarioEntidade buscarUsuario(Long cpf) throws RegraValidacaoException {
		UsuarioEntidade usuario = usuarioDAO.buscarPorCPF(cpf);

		if (usuarioNaoEncontrado(usuario)) {
			throw new RegraValidacaoException(MensagemErroEnum.CPF_INEXISTENTE);
		}
		return usuario;
	}
}