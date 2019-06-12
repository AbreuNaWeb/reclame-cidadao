package br.com.principal.tela.visitante;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.principal.constante.LinkPaginaEnum;
import br.com.principal.constante.MensagemEnum;
import br.com.principal.constante.MensagemErroEnum;
import br.com.principal.entidade.UsuarioEntidade;
import br.com.principal.excecao.RegraValidacaoException;
import br.com.principal.regra.UsuarioRegras;
import br.com.principal.tela.util.TelaUtil;

@Named
@RequestScoped
public class CadastroUsuarioTela {

	@Inject
	private UsuarioRegras usuarioRegras;

	private UsuarioEntidade usuarioRegistro;

	@PostConstruct
	public void inicializar() {
		this.usuarioRegistro = new UsuarioEntidade();
	}

	public void cadastrar() {
		try {
			usuarioRegras.cadastrarUsuario(usuarioRegistro);
			TelaUtil.redirecionarParaOutraPagina(LinkPaginaEnum.LOGIN.getLink(), MensagemEnum.CADASTROU_SUCESSO.getDescricao());
		} catch (RegraValidacaoException erroValidacao) {
			TelaUtil.adicionarMensagemDeErro(erroValidacao.getMensagemErroEnum());
		} catch (Exception erroDesconhecido) {
			TelaUtil.adicionarMensagemDeErro(MensagemErroEnum.ERRO_DESCONHECIDO);
		}
	}

	public UsuarioEntidade getusuarioRegistro() {
		return usuarioRegistro;
	}

	public void setusuarioRegistro(UsuarioEntidade usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}
}