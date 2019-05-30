package br.com.principal.tela;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.principal.constante.MensagemDeErroEnum;
import br.com.principal.constante.TipoUsuarioEnum;
import br.com.principal.entidade.UsuarioEntidade;
import br.com.principal.regra.UsuarioRegras;

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
			cadastrarCidadao();
		} else if (agenteAindaNaoSeCadastrou(usuarioPesquisada)) {
			atualizarDadosDoAgente(usuarioPesquisada);
		} else {
			TelaUtil.adicionarMensagemDeErro(MensagemDeErroEnum.CPF_JA_CADASTRADO);
		}
	}

	private void cadastrarCidadao() {
		this.usuarioRegistro.setTipo(TipoUsuarioEnum.CIDADAO.getDescricao());
		usuarioRegras.salvar(usuarioRegistro);
	}

	private void atualizarDadosDoAgente(UsuarioEntidade usuarioPesquisada) {
		usuarioPesquisada.setCpf(this.usuarioRegistro.getCpf());
		usuarioPesquisada.setEmail(this.usuarioRegistro.getEmail());
		usuarioPesquisada.setSenha(this.usuarioRegistro.getSenha());
		usuarioPesquisada.setDdd(this.usuarioRegistro.getDdd());
		usuarioPesquisada.setNumeroCelular(this.usuarioRegistro.getNumeroCelular());
		usuarioRegras.atualizar(usuarioPesquisada);
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