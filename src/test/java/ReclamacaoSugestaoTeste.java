import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.principal.constante.MensagemErroEnum;
import br.com.principal.entidade.ReclamacaoSugestaoEntidade;
import br.com.principal.entidade.UsuarioEntidade;
import br.com.principal.excecao.RegraValidacaoException;
import br.com.principal.persistencia.ReclamacaoSugestaoDAO;
import br.com.principal.regra.ReclamacaoSugestaoRegras;
import fabrica.ReclamacaoSugestaoFabrica;
import fabrica.UsuarioFabrica;

@RunWith(MockitoJUnitRunner.class)
public class ReclamacaoSugestaoTeste {

	@InjectMocks
	ReclamacaoSugestaoRegras regra;

	@Mock
	private ReclamacaoSugestaoDAO reclamacaoSugestaoDAO;

	@Test(expected = RegraValidacaoException.class)
	public void atingiuLimiteDeReclamacoesSugestoesAbertasTeste() throws RegraValidacaoException {
		UsuarioEntidade usuarioEntidade = UsuarioFabrica.criarComCPF();
		ReclamacaoSugestaoEntidade reclamacaoSugestaoEntidade = ReclamacaoSugestaoFabrica.criarComStatusAberta();
		List<ReclamacaoSugestaoEntidade> reclamacoesSugestoesAbertas = new ArrayList<ReclamacaoSugestaoEntidade>(0);
		reclamacoesSugestoesAbertas.add(reclamacaoSugestaoEntidade);
		reclamacoesSugestoesAbertas.add(reclamacaoSugestaoEntidade);
		reclamacoesSugestoesAbertas.add(reclamacaoSugestaoEntidade);
		reclamacoesSugestoesAbertas.add(reclamacaoSugestaoEntidade);
		reclamacoesSugestoesAbertas.add(reclamacaoSugestaoEntidade);
		reclamacoesSugestoesAbertas.add(reclamacaoSugestaoEntidade);

		Mockito.when(reclamacaoSugestaoDAO.buscarReclamacoesOuSugestoesAbertasDoCidadao(usuarioEntidade.getCpf()))
				.thenReturn(reclamacoesSugestoesAbertas);

		try {
			regra.verificarSeExedeuLimites(usuarioEntidade);
		} catch (RegraValidacaoException erroValidacao) {
			Assert.assertEquals(MensagemErroEnum.ATINGIU_LIMITE_RECLAMACOES_SUGESTOES_ABERTAS.getDescricao(),
					erroValidacao.getMensagemErroEnum().getDescricao());
			throw erroValidacao;
		}
	}

	@Test(expected = RegraValidacaoException.class)
	public void atingiuLimiteDeReclamacoesSugestoesCadastradasPorDiaTeste() throws RegraValidacaoException {
		UsuarioEntidade usuarioEntidade = UsuarioFabrica.criarComCPF();
		ReclamacaoSugestaoEntidade reclamacaoSugestaoEntidade = ReclamacaoSugestaoFabrica.criarComCadastroHoje();
		List<ReclamacaoSugestaoEntidade> reclamacoesSugestoesCadastradasHoje = new ArrayList<ReclamacaoSugestaoEntidade>(
				0);
		reclamacoesSugestoesCadastradasHoje.add(reclamacaoSugestaoEntidade);
		reclamacoesSugestoesCadastradasHoje.add(reclamacaoSugestaoEntidade);
		reclamacoesSugestoesCadastradasHoje.add(reclamacaoSugestaoEntidade);

		Mockito.when(reclamacaoSugestaoDAO.buscarReclamacoesOuSugestoesAbertasDoCidadao(usuarioEntidade.getCpf()))
				.thenReturn(new ArrayList<ReclamacaoSugestaoEntidade>(0));

		Mockito.when(reclamacaoSugestaoDAO.buscarReclamacoesOuSugestoesCadastradasHoje(usuarioEntidade.getCpf()))
				.thenReturn(reclamacoesSugestoesCadastradasHoje);

		try {
			regra.verificarSeExedeuLimites(usuarioEntidade);
		} catch (RegraValidacaoException erroValidacao) {
			Assert.assertEquals(MensagemErroEnum.ATINGIU_LIMITE_RECLAMACOES_SUGESTOES_CADASTRADAS_DIA.getDescricao(),
					erroValidacao.getMensagemErroEnum().getDescricao());
			throw erroValidacao;
		}
	}
}
