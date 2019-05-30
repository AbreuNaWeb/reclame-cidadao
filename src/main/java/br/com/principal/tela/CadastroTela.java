package br.com.principal.tela;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.principal.constante.MensagemDeErroEnum;
import br.com.principal.constante.TipoUsuarioEnum;
import br.com.principal.entidade.PessoaEntidade;
import br.com.principal.regra.PessoaRegras;

@Named
@RequestScoped
public class CadastroTela {

	@Inject
	private PessoaRegras pessoaRegras;

	private PessoaEntidade pessoaRegistro;

	private boolean cpfDeAgente;
	
	@PostConstruct
	public void inicializar() {
		this.pessoaRegistro = new PessoaEntidade();
	}
	
	public void cadastrar() {
		PessoaEntidade pessoaPesquisada = pessoaRegras.buscarPorCPF(this.pessoaRegistro.getCpf());

		if (cpfAindaNaoCadastrado(pessoaPesquisada)) {
			cadastrarCidadao();
		} else if (agenteAindaNaoSeCadastrou(pessoaPesquisada)) {
			atualizarDadosDoAgente(pessoaPesquisada);
		} else {
			TelaUtil.adicionarMensagemDeErro(MensagemDeErroEnum.CPF_JA_CADASTRADO);
		}
	}

	private void cadastrarCidadao() {
		this.pessoaRegistro.setTipo(TipoUsuarioEnum.CIDADAO.getDescricao());
		pessoaRegras.salvar(pessoaRegistro);
	}

	private void atualizarDadosDoAgente(PessoaEntidade pessoaPesquisada) {
		pessoaPesquisada.setCpf(this.pessoaRegistro.getCpf());
		pessoaPesquisada.setEmail(this.pessoaRegistro.getEmail());
		pessoaPesquisada.setSenha(this.pessoaRegistro.getSenha());
		pessoaPesquisada.setDdd(this.pessoaRegistro.getDdd());
		pessoaPesquisada.setNumeroCelular(this.pessoaRegistro.getNumeroCelular());
		pessoaRegras.atualizar(pessoaPesquisada);
	}

	private boolean agenteAindaNaoSeCadastrou(PessoaEntidade pessoaPesquisada) {
		return TipoUsuarioEnum.AGENTE.igual(pessoaPesquisada) && pessoaPesquisada.getDataCadastro() == null;
	}

	private boolean cpfAindaNaoCadastrado(PessoaEntidade pessoaPesquisada) {
		return pessoaPesquisada == null;
	}

	public PessoaEntidade getPessoaRegistro() {
		return pessoaRegistro;
	}
	
	public void setPessoaRegistro(PessoaEntidade pessoaRegistro) {
		this.pessoaRegistro = pessoaRegistro;
	}
	
	public boolean isCpfDeAgente() {
		return cpfDeAgente;
	}
}