package br.com.principal.tela;

import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.principal.constante.MensagemEnum;
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
			TelaUtil.redirecionarParaOutraPagina("login.xhtml", MensagemEnum.GRAVOU_SUCESSO.getDescricao());
		} else if (agenteAindaNaoSeCadastrou(usuarioPesquisada)) {
			atualizarDadosDoAgente(usuarioPesquisada);
			TelaUtil.redirecionarParaOutraPagina("login.xhtml", MensagemEnum.GRAVOU_SUCESSO.getDescricao());
		} else {
			TelaUtil.adicionarMensagemDeErro(MensagemEnum.CPF_JA_CADASTRADO);
		}
	}

	private void cadastrarCidadao() {
		this.usuarioRegistro.setTipo(TipoUsuarioEnum.CIDADAO.getDescricao());
		usuarioRegras.salvar(usuarioRegistro);
	}

	private void atualizarDadosDoAgente(UsuarioEntidade usuarioPesquisada) {
		usuarioPesquisada.setEmail(this.usuarioRegistro.getEmail());
		usuarioPesquisada.setSenha(this.usuarioRegistro.getSenha());
		usuarioPesquisada.setDdd(this.usuarioRegistro.getDdd());
		usuarioPesquisada.setNumeroCelular(this.usuarioRegistro.getNumeroCelular());
		usuarioPesquisada.setDataCadastro(TelaUtil.converterCalendarParaDiaMesAno(Calendar.getInstance()));
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