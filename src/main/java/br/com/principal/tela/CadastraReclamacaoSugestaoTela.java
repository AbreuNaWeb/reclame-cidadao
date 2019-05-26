package br.com.principal.tela;

import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import br.com.principal.constante.CategoriasEnum;
import br.com.principal.constante.MensagemDeErroEnum;
import br.com.principal.constante.TipoReclamacaoSugestaoEnum;
import br.com.principal.constante.UnidadeFederativaEnum;
import br.com.principal.entidade.EnderecoEntidade;
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
		this.novaReclamacaoSugestao = new ReclamacaoSugestaoEntidade();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void cadastrar() {
		EnderecoEntidade endereco = novaReclamacaoSugestao.getEndereco();

		if (informouEstado(endereco) && TelaUtil.campoNaoInformado(endereco.getRua(), endereco.getNumero(),
				endereco.getBairro(), endereco.getCidade())) {
			TelaUtil.adicionarMensagemDeErro(MensagemDeErroEnum.INFORAR_CAMPOS_ENDERECO);
		} else {
			novaReclamacaoSugestao.setHoraCriacao(TelaUtil.converterCalendarParaHoraMinuto(Calendar.getInstance()));
			this.novaReclamacaoSugestao = regra.salvar(novaReclamacaoSugestao);
			TelaUtil.redirecionarParaOutraPagina("detalheReclamacaoSugestao.xhtml?id=" + novaReclamacaoSugestao.getId(), "Gravou com sucesso");
		}
	}

	private boolean informouEstado(EnderecoEntidade endereco) {
		return StringUtils.isNotBlank(endereco.getEstado());
	}

	public TipoReclamacaoSugestaoEnum[] opcoesDeTipo() {
		return TipoReclamacaoSugestaoEnum.values();
	}

	public CategoriasEnum[] categorias() {
		return CategoriasEnum.values();
	}

	public UnidadeFederativaEnum[] unidadesFederativas() {
		return UnidadeFederativaEnum.values();
	}

	public ReclamacaoSugestaoEntidade getNovaReclamacaoSugestao() {
		return novaReclamacaoSugestao;
	}

	public void setNovaReclamacaoSugestao(ReclamacaoSugestaoEntidade novaReclamacaoSugestao) {
		this.novaReclamacaoSugestao = novaReclamacaoSugestao;
	}
}