package br.com.principal.tela.todos;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.principal.constante.MensagemEnum;
import br.com.principal.constante.StatusReclamacaoSugestaoEnum;
import br.com.principal.constante.TipoUsuarioEnum;
import br.com.principal.entidade.ReclamacaoSugestaoEntidade;
import br.com.principal.entidade.UsuarioEntidade;
import br.com.principal.regra.ReclamacaoSugestaoRegras;
import br.com.principal.tela.util.SessaoUtil;
import br.com.principal.tela.util.TelaUtil;

@Named
@ViewScoped
public class DetalheReclamacaoSugestaoTela implements Serializable {

	private static final long serialVersionUID = 1L;

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
	
	public List<StatusReclamacaoSugestaoEnum> statusDisponiveisAposAnalise() {
		return Arrays.asList(StatusReclamacaoSugestaoEnum.CONCLUIDA, StatusReclamacaoSugestaoEnum.EM_ANDAMENTO, StatusReclamacaoSugestaoEnum.INDEFERIDA);
	}
	
	public void atribuirReclamacaoOuSugestaoParaAgente() {
		regra.atribuirReclamacaoOuSugestaoParaAgente(this.reclamacaoSugestaoRegistro, SessaoUtil.obterUsuarioLogado());
		TelaUtil.adicionarMensagemDeInformacao(MensagemEnum.DADOS_ATUALIZADOS_SUCESSO);
	}
	
	public boolean agentePodeConcluirOuIndeferirReclamacaoOuSugestao() {
		UsuarioEntidade usuario = SessaoUtil.obterUsuarioLogado();
		return TipoUsuarioEnum.AGENTE.igual(usuario) && StatusReclamacaoSugestaoEnum.EM_ANALISE.igual(reclamacaoSugestaoRegistro) && reclamacaoSugestaoRegistro.getCategoria().equals(usuario.getSetor()) 
				&& reclamacaoSugestaoRegistro.getAgente().getCpf().equals(usuario.getCpf());
	}
	
	public boolean agentePodeAtribuirReclamacaoOuSugestaoParaSi() {
		UsuarioEntidade usuario = SessaoUtil.obterUsuarioLogado();
		return TipoUsuarioEnum.AGENTE.igual(usuario) && StatusReclamacaoSugestaoEnum.ABERTA.igual(reclamacaoSugestaoRegistro) && reclamacaoSugestaoRegistro.getCategoria().equals(usuario.getSetor());
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