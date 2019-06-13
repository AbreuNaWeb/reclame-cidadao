import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.principal.constante.MensagemErroEnum;
import br.com.principal.entidade.UsuarioEntidade;
import br.com.principal.excecao.RegraValidacaoException;
import br.com.principal.persistencia.UsuarioDAO;
import br.com.principal.regra.ReclamacaoSugestaoRegras;
import br.com.principal.regra.UsuarioRegras;
import fabrica.UsuarioFabrica;

@RunWith(MockitoJUnitRunner.class)
public class UsuarioTeste {

	@Spy
	@InjectMocks
	private UsuarioRegras regra;

	@Mock
	private UsuarioDAO usuarioDAO;

	@Mock
	private ReclamacaoSugestaoRegras reclamacaoSugestaoRegras;

	@Test
	public void salvarCidadaoAoCadastrarTeste() throws RegraValidacaoException {
		UsuarioEntidade usuarioEntidade = UsuarioFabrica.criarComCPF();

		regra.cadastrarUsuario(usuarioEntidade);

		Mockito.verify(regra).salvarCidadao(usuarioEntidade);
		Mockito.verify(usuarioDAO).salvar(usuarioEntidade);
	}

	@Test
	public void atualizarDadosAgenteAoCadastrarTeste() throws RegraValidacaoException {
		UsuarioEntidade usuarioEntidade = UsuarioFabrica.criarAgente();
		Mockito.when(usuarioDAO.buscarPorCPF(usuarioEntidade.getCpf())).thenReturn(usuarioEntidade);

		regra.cadastrarUsuario(usuarioEntidade);

		Mockito.verify(regra).atualizarComConversaoDeSenhaParaMD5(usuarioEntidade);
	}

	@Test(expected = RegraValidacaoException.class)
	public void usuarioJaCadastradoAoCadastrarUsuarioTeste() throws RegraValidacaoException {
		UsuarioEntidade usuarioEntidade = UsuarioFabrica.criarComCPF();
		Mockito.when(usuarioDAO.buscarPorCPF(usuarioEntidade.getCpf())).thenReturn(usuarioEntidade);

		try {
			regra.cadastrarUsuario(usuarioEntidade);
		} catch (RegraValidacaoException erroValidacao) {
			Assert.assertEquals(MensagemErroEnum.CPF_JA_CADASTRADO.getDescricao(),
					erroValidacao.getMensagemErroEnum().getDescricao());
			throw erroValidacao;
		}
	}

	@Test(expected = RegraValidacaoException.class)
	public void usuarioNaoEncontradoAoRealizarLoginTeste() throws RegraValidacaoException {
		try {
			regra.realizarLogin(24433L, "12345");
		} catch (RegraValidacaoException erroValidacao) {
			Assert.assertEquals(MensagemErroEnum.LOGIN_ERRADO.getDescricao(),
					erroValidacao.getMensagemErroEnum().getDescricao());
			throw erroValidacao;
		}
	}

	@Test(expected = RegraValidacaoException.class)
	public void cidadaoBloqueadoAoRealizarLoginTeste() throws RegraValidacaoException {
		try {
			Long cpfInformado = 1234567891L;
			String senhaInformada = "12345";
			Mockito.when(usuarioDAO.buscarPorCpfESenhaEmMD5(cpfInformado, regra.converterSenhaParaMD5(senhaInformada)))
					.thenReturn(UsuarioFabrica.criarBloqueado());
			regra.realizarLogin(cpfInformado, senhaInformada);
		} catch (RegraValidacaoException erroValidacao) {
			Assert.assertEquals(MensagemErroEnum.VOCE_FOI_BLOQUEADO.getDescricao(),
					erroValidacao.getMensagemErroEnum().getDescricao());
			throw erroValidacao;
		}
	}

	@Test(expected = RegraValidacaoException.class)
	public void cpfInformadoNaoPertenceCidadaoAoBloquearTeste() throws RegraValidacaoException {
		UsuarioEntidade usuarioEntidade = UsuarioFabrica.criarAgente();
		Mockito.when(usuarioDAO.buscarPorCPF(usuarioEntidade.getCpf())).thenReturn(usuarioEntidade);

		try {
			regra.buscarCidadaoParaBloquear(usuarioEntidade.getCpf());
		} catch (RegraValidacaoException erroValidacao) {
			Assert.assertEquals(MensagemErroEnum.CPF_INFORMADO_NAO_PERTENCE_CIDADAO.getDescricao(),
					erroValidacao.getMensagemErroEnum().getDescricao());
			throw erroValidacao;
		}
	}

	@Test(expected = RegraValidacaoException.class)
	public void cidadaoJaBloqueadoAoBloquearTeste() throws RegraValidacaoException {
		UsuarioEntidade usuarioEntidade = UsuarioFabrica.criarBloqueado();
		Mockito.when(usuarioDAO.buscarPorCPF(usuarioEntidade.getCpf())).thenReturn(usuarioEntidade);

		try {
			regra.buscarCidadaoParaBloquear(usuarioEntidade.getCpf());
		} catch (RegraValidacaoException erroValidacao) {
			Assert.assertEquals(MensagemErroEnum.CIDADAO_JA_BLOQUEADO.getDescricao(),
					erroValidacao.getMensagemErroEnum().getDescricao());
			throw erroValidacao;
		}
	}

	@Test
	public void salvarAgenteTeste() throws RegraValidacaoException {
		UsuarioEntidade usuarioEntidade = UsuarioFabrica.criarComCPF();

		regra.salvarAgente(usuarioEntidade);

		Mockito.verify(usuarioDAO).salvar(usuarioEntidade);
	}

	@Test(expected = RegraValidacaoException.class)
	public void agenteJaCadastradoAoCadastrarAgenteTeste() throws RegraValidacaoException {
		UsuarioEntidade usuarioEntidade = UsuarioFabrica.criarComCPF();
		Mockito.when(usuarioDAO.buscarPorCPF(usuarioEntidade.getCpf())).thenReturn(usuarioEntidade);

		try {
			regra.salvarAgente(usuarioEntidade);
		} catch (RegraValidacaoException erroValidacao) {
			Assert.assertEquals(MensagemErroEnum.CPF_JA_CADASTRADO.getDescricao(),
					erroValidacao.getMensagemErroEnum().getDescricao());
			throw erroValidacao;
		}
	}

	@Test(expected = RegraValidacaoException.class)
	public void cpfInformadoNaoPertenceAgenteAoBuscarAgenteParaAtualizarOuExcluirTeste()
			throws RegraValidacaoException {
		UsuarioEntidade usuarioEntidade = UsuarioFabrica.criarAdministrador();
		Mockito.when(usuarioDAO.buscarPorCPF(usuarioEntidade.getCpf())).thenReturn(usuarioEntidade);

		try {
			regra.buscarAgenteParaAtualizarOuExcluir(usuarioEntidade.getCpf());
		} catch (RegraValidacaoException erroValidacao) {
			Assert.assertEquals(MensagemErroEnum.CPF_INFORMADO_NAO_PERTENCE_AGENTE.getDescricao(),
					erroValidacao.getMensagemErroEnum().getDescricao());
			throw erroValidacao;
		}
	}

	@Test(expected = RegraValidacaoException.class)
	public void existeReclamacaoOuSugestaoAbertaVinculadaAoBuscarAgenteParaAtualizarOuExcluirTeste()
			throws RegraValidacaoException {
		UsuarioEntidade usuarioEntidade = UsuarioFabrica.criarAgente();
		Mockito.when(usuarioDAO.buscarPorCPF(usuarioEntidade.getCpf())).thenReturn(usuarioEntidade);
		Mockito.when(reclamacaoSugestaoRegras.existeReclamacaoOuSugestaoAbertaVinculadaAoAgente(usuarioEntidade.getCpf()))
				.thenReturn(true);

		try {
			regra.buscarAgenteParaAtualizarOuExcluir(usuarioEntidade.getCpf());
		} catch (RegraValidacaoException erroValidacao) {
			Assert.assertEquals(MensagemErroEnum.RECLAMACAO_SUGESTAO_ABERTA_AGENTE.getDescricao(),
					erroValidacao.getMensagemErroEnum().getDescricao());
			throw erroValidacao;
		}
	}
}