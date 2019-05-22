package br.com.principal.tela;

import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.principal.constante.TipoReclamacaoSugestaoEnum;
import br.com.principal.entidade.ReclamacaoSugestaoEntidade;
import br.com.principal.regra.ReclamacaoSugestaoRegras;

@Named
@RequestScoped
public class CadastraReclamacaoSugestaoTela {

	@Inject
	private ReclamacaoSugestaoRegras regra;
	
	private ReclamacaoSugestaoEntidade novaReclamacaoSugestao;
	
	@PostConstruct
	public void inicializar() {
		novaReclamacaoSugestao = new ReclamacaoSugestaoEntidade();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void cadastrar() {
		novaReclamacaoSugestao.setDataCriacao(Calendar.getInstance());
		regra.salvar(novaReclamacaoSugestao);
	}
	
	public TipoReclamacaoSugestaoEnum[] opcoesDeTipo() {
		return TipoReclamacaoSugestaoEnum.values();
	}

	public ReclamacaoSugestaoEntidade getNovaReclamacaoSugestao() {
		return novaReclamacaoSugestao;
	}

	public void setNovaReclamacaoSugestao(ReclamacaoSugestaoEntidade novaReclamacaoSugestao) {
		this.novaReclamacaoSugestao = novaReclamacaoSugestao;
	}
}