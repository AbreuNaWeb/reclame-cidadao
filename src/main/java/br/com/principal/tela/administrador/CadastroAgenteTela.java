package br.com.principal.tela.administrador;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.principal.constante.CategoriasEnum;
import br.com.principal.constante.MensagemEnum;
import br.com.principal.constante.MensagemErroEnum;
import br.com.principal.entidade.UsuarioEntidade;
import br.com.principal.excecao.RegraValidacaoException;
import br.com.principal.regra.UsuarioRegras;
import br.com.principal.tela.util.SessaoUtil;
import br.com.principal.tela.util.TelaUtil;

@Named
@RequestScoped
public class CadastroAgenteTela {

	@Inject
	private UsuarioRegras usuarioRegras;

	private UsuarioEntidade novoAgente;

	private boolean possuiPermissao;
	
	@PostConstruct
	public void inicializar() {
		this.novoAgente = new UsuarioEntidade();
	}
	
	public void exibeMensagemCasoNaoPossuirPermissao() {
		this.possuiPermissao = SessaoUtil.isNotAdministrador() ? false : true;
		
		if (!possuiPermissao) {
			TelaUtil.adicionarMensagemDeErro(MensagemErroEnum.VOCE_NAO_TEM_PERMISSAO);
		}
	}

	public void cadastrar() {
		try {
			usuarioRegras.salvarAgente(novoAgente);
			this.novoAgente = new UsuarioEntidade();
			TelaUtil.adicionarMensagemDeInformacao(MensagemEnum.CADASTROU_SUCESSO);
		} catch (RegraValidacaoException erroValidacao) {
			TelaUtil.adicionarMensagemDeErro(erroValidacao.getMensagemErroEnum());
		} catch (Exception erroDesconhecido) {
			TelaUtil.adicionarMensagemDeErro(MensagemErroEnum.ERRO_DESCONHECIDO);
		}
	}

	public CategoriasEnum[] categorias() {
		return CategoriasEnum.values();
	}

	public UsuarioEntidade getNovoAgente() {
		return novoAgente;
	}

	public void setNovoAgente(UsuarioEntidade novoAgente) {
		this.novoAgente = novoAgente;
	}
	
	public boolean isPossuiPermissao() {
		return possuiPermissao;
	}
}