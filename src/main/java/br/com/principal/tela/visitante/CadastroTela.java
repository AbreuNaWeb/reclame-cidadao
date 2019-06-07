package br.com.principal.tela.visitante;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.principal.constante.MensagemEnum;
import br.com.principal.constante.TipoUsuarioEnum;
import br.com.principal.entidade.UsuarioEntidade;
import br.com.principal.regra.UsuarioRegras;
import br.com.principal.tela.util.TelaUtil;

@Named
@RequestScoped
public class CadastroTela {

	@Inject
	private UsuarioRegras usuarioRegras;

	private UsuarioEntidade usuarioRegistro;

	private boolean cpfDeAgente;
	
	@PostConstruct
	public void inicializar() {
		this.usuarioRegistro = new UsuarioEntidade();
	}
	
	public void cadastrar() {
		UsuarioEntidade usuarioPesquisada = usuarioRegras.buscarPorCPF(this.usuarioRegistro.getCpf());

		if (cpfAindaNaoCadastrado(usuarioPesquisada)) {
			usuarioRegras.salvarCidadao(usuarioRegistro);
			TelaUtil.redirecionarParaOutraPagina("login.xhtml", MensagemEnum.CADASTROU_SUCESSO.getDescricao());
		} else if (agenteAindaNaoSeCadastrou(usuarioPesquisada)) {
			atualizarDadosDoAgente(usuarioPesquisada);
			TelaUtil.redirecionarParaOutraPagina("login.xhtml", MensagemEnum.CADASTROU_SUCESSO.getDescricao());
		} else {
			TelaUtil.adicionarMensagemDeErro(MensagemEnum.CPF_JA_CADASTRADO);
		}
	}

	private void atualizarDadosDoAgente(UsuarioEntidade usuarioPesquisada) {
		usuarioPesquisada.setEmail(this.usuarioRegistro.getEmail());
		usuarioPesquisada.setSenha(this.usuarioRegistro.getSenha());
		usuarioPesquisada.setDddCelular(null);
		usuarioPesquisada.setDataCadastro(TelaUtil.diaAtualEmFormatoDiaMesAno());
		usuarioRegras.atualizarComConversaoDeSenhaParaMD5(usuarioPesquisada);
	}

	private boolean agenteAindaNaoSeCadastrou(UsuarioEntidade usuarioPesquisada) {
		return TipoUsuarioEnum.AGENTE.igual(usuarioPesquisada) && usuarioPesquisada.getDataCadastro() == null;
	}

	private boolean cpfAindaNaoCadastrado(UsuarioEntidade usuarioPesquisada) {
		return usuarioPesquisada == null;
	}

	public UsuarioEntidade getusuarioRegistro() {
		return usuarioRegistro;
	}
	
	public void setusuarioRegistro(UsuarioEntidade usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}
	
	public boolean isCpfDeAgente() {
		return cpfDeAgente;
	}
}