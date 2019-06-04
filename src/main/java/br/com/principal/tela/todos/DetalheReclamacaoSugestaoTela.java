package br.com.principal.tela.todos;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.principal.constante.MensagemEnum;
import br.com.principal.entidade.ReclamacaoSugestaoEntidade;
import br.com.principal.regra.ReclamacaoSugestaoRegras;
import br.com.principal.tela.util.TelaUtil;

@Named
@RequestScoped
public class DetalheReclamacaoSugestaoTela {

	@Inject
	private ReclamacaoSugestaoRegras regra;

	private ReclamacaoSugestaoEntidade reclamacaoSugestaoRegistro;

	private Long id;

	public void inicializar() {
		if (informadoID()) {
			this.reclamacaoSugestaoRegistro = regra.buscarPorID(id);
		} else {
			TelaUtil.adicionarMensagemDeErro(MensagemEnum.ERRO_FAVOR_RETORNAR_PAGINA_INICIAL);
		}
	}

	public ReclamacaoSugestaoEntidade getReclamacaoSugestaoRegistro() {
		return reclamacaoSugestaoRegistro;
	}

	public void setReclamacaoSugestaoRegistro(ReclamacaoSugestaoEntidade reclamacaoSugestaoRegistro) {
		this.reclamacaoSugestaoRegistro = reclamacaoSugestaoRegistro;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	private boolean informadoID() {
		return id != null;
	}
}