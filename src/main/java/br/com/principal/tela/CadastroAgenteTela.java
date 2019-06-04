package br.com.principal.tela;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.principal.constante.CategoriasEnum;
import br.com.principal.constante.MensagemEnum;
import br.com.principal.entidade.UsuarioEntidade;
import br.com.principal.excecao.RegraValidacaoException;
import br.com.principal.regra.UsuarioRegras;

@Named
@RequestScoped
public class CadastroAgenteTela {

	@Inject
	private UsuarioRegras usuarioRegras;

	private UsuarioEntidade novoAgente;

	@PostConstruct
	public void inicializar() {
		this.novoAgente = new UsuarioEntidade();
	}

	public void cadastrar() {
		try {
			usuarioRegras.salvarAgente(novoAgente);
			this.novoAgente = new UsuarioEntidade();
			TelaUtil.adicionarMensagemDeInformacao(MensagemEnum.CADASTROU_SUCESSO);
		} catch (RegraValidacaoException erroValidacao) {
			TelaUtil.adicionarMensagemDeErro(erroValidacao.getMensagemEnum());
		} catch (Exception erroDesconhecido) {
			TelaUtil.adicionarMensagemDeErro(MensagemEnum.ERRO_DESCONHECIDO);
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
}