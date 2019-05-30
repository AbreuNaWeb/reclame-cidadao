package br.com.principal.tela;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.com.principal.constante.MensagemDeErroEnum;
import br.com.principal.entidade.UsuarioEntidade;
import br.com.principal.regra.UsuarioRegras;

@Named
@SessionScoped
public class LoginTela implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioRegras usuarioRegras;
	
	private Long cpf;
	
	private String senha;
	
	private UsuarioEntidade usuario;
	
	public boolean usuarioLogado() {
		return this.usuario != null;
	}
	
	public String realizarLogin() {
		try {
			this.usuario = usuarioRegras.buscarPorCpfESenhaEmMD5(cpf, senha);

			if (usuarioNaoEncontrado(usuario)) {
				return retornarMensagemDeUsuarioNaoEncontrado();
			}

			return redirecionarParaPaginaInicial(usuario);
		} catch (Exception excecao) {
			return tratarErroDesconhecido();
		}
	}
	
	public String deslogar() {
		HttpSession session = SessaoUtil.obterSessao();
		session.invalidate();
		return "/home.xhtml?faces-redirect=true";
	}

	private String tratarErroDesconhecido() {
		TelaUtil.adicionarMensagemDeErro(MensagemDeErroEnum.ERRO_REALIZAR_LOGIN);
		FacesContext.getCurrentInstance().validationFailed();
		return "";
	}

	private String redirecionarParaPaginaInicial(final UsuarioEntidade usuario) {
		HttpSession session = SessaoUtil.obterSessao();
		session.setAttribute("usuarioLogado", usuario);
		return "/home.xhtml?faces-redirect=true";
	}

	private String retornarMensagemDeUsuarioNaoEncontrado() {
		TelaUtil.adicionarMensagemDeErro(MensagemDeErroEnum.LOGIN_ERRADO);
		FacesContext.getCurrentInstance().validationFailed();
		return "";
	}

	private boolean usuarioNaoEncontrado(final UsuarioEntidade usuario) {
		return usuario == null;
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