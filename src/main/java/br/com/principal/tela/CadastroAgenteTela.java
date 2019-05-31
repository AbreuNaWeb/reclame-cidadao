package br.com.principal.tela;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.principal.constante.CategoriasEnum;
import br.com.principal.constante.MensagemEnum;
import br.com.principal.entidade.UsuarioEntidade;
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
		UsuarioEntidade usuarioPesquisado = usuarioRegras.buscarPorCPF(novoAgente.getCpf());
		
		if (cpfAindaNaoCadastrado(usuarioPesquisado)) {
			usuarioRegras.salvarAgente(novoAgente);
			TelaUtil.redirecionarParaOutraPagina("", MensagemEnum.CADASTROU_SUCESSO.getDescricao());
		} else {
			TelaUtil.adicionarMensagemDeErro(MensagemEnum.CPF_JA_CADASTRADO);
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
	
	private boolean cpfAindaNaoCadastrado(UsuarioEntidade usuarioPesquisado) {
		return usuarioPesquisado == null;
	}
}