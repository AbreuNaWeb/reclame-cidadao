package br.com.principal.tela.visitante;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.principal.constante.LinkPaginaEnum;
import br.com.principal.constante.MensagemErroEnum;
import br.com.principal.entidade.UsuarioEntidade;
import br.com.principal.excecao.RegraValidacaoException;
import br.com.principal.regra.UsuarioRegras;
import br.com.principal.tela.util.SessaoUtil;
import br.com.principal.tela.util.TelaUtil;

@Named
@SessionScoped
public class LoginTela implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioRegras usuarioRegras;

	private Long cpf;

	private String senha;

	private UsuarioEntidade usuario;

	public String realizarLogin() {
		try {
			this.usuario = usuarioRegras.realizarLogin(cpf, senha);
			return redirecionarParaPaginaInicial(usuario);
		} catch (RegraValidacaoException erroValidacao) {
			return retornarMensagemDeUsuarioNaoEncontrado(erroValidacao.getMensagemErroEnum());
		} catch (Exception erroDesconhecido) {
			return tratarErroDesconhecido();
		}
	}

	public boolean usuarioLogado() {
		return this.usuario != null;
	}

	public String deslogar() {
		HttpSession sessao = SessaoUtil.obterSessao();
		sessao.invalidate();
		return LinkPaginaEnum.HOME.getLink();
	}

	private String tratarErroDesconhecido() {
		TelaUtil.adicionarMensagemDeErro(MensagemErroEnum.ERRO_REALIZAR_LOGIN);
		FacesContext.getCurrentInstance().validationFailed();
		return LinkPaginaEnum.PAGINA_ATUAL.getLink();
	}

	private String redirecionarParaPaginaInicial(UsuarioEntidade usuario) {
		HttpSession sessao = SessaoUtil.obterSessao();
		sessao.setAttribute("usuarioLogado", usuario);
		return LinkPaginaEnum.HOME.getLink();
	}

	private String retornarMensagemDeUsuarioNaoEncontrado(MensagemErroEnum mensagemErroEnum) {
		TelaUtil.adicionarMensagemDeErro(mensagemErroEnum);
		FacesContext.getCurrentInstance().validationFailed();
		return LinkPaginaEnum.PAGINA_ATUAL.getLink();
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}