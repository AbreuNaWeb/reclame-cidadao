package br.com.principal.tela.todos;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import br.com.principal.constante.MensagemEnum;
import br.com.principal.constante.MensagemErroEnum;
import br.com.principal.constante.StatusReclamacaoSugestaoEnum;
import br.com.principal.constante.TipoUsuarioEnum;
import br.com.principal.entidade.ReclamacaoSugestaoEntidade;
import br.com.principal.entidade.UsuarioEntidade;
import br.com.principal.regra.ReclamacaoSugestaoRegras;
import br.com.principal.regra.UsuarioRegras;
import br.com.principal.tela.util.SessaoUtil;
import br.com.principal.tela.util.TelaUtil;

@Named
@ViewScoped
public class DetalheReclamacaoSugestaoTela implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ReclamacaoSugestaoRegras reclamacaoSugestaoRegra;
	
	@Inject
	private UsuarioRegras usuarioRegras;

	private ReclamacaoSugestaoEntidade reclamacaoSugestaoRegistro;

	private Long id;

	public void inicializar() {
		if (informadoID()) {
			this.reclamacaoSugestaoRegistro = reclamacaoSugestaoRegra.buscarPorID(id);
		} else {
			TelaUtil.adicionarMensagemDeErro(MensagemErroEnum.ERRO_FAVOR_RETORNAR_PAGINA_INICIAL);
		}
	}

	public void atualizarReclamacaoOuSugestaoPorAgente() {
		reclamacaoSugestaoRegra.atualizar(reclamacaoSugestaoRegistro);
		usuarioRegras.marcarParaMostrarNotificacao(reclamacaoSugestaoRegistro.getCidadao());
	}
	
	public void atualizarReclamacaoOuSugestaoPorCidadao() {
		reclamacaoSugestaoRegra.atualizar(reclamacaoSugestaoRegistro);
	}
	
	public boolean cidadaoAtribuiuNota() {
		return reclamacaoSugestaoRegistro.getNota() != null;
	}

	public List<StatusReclamacaoSugestaoEnum> statusDisponiveisQuandoEmAnaliseEmAndamento() {
		if (StatusReclamacaoSugestaoEnum.EM_ANALISE.igual(reclamacaoSugestaoRegistro)) {
			return Arrays.asList(StatusReclamacaoSugestaoEnum.CONCLUIDA, StatusReclamacaoSugestaoEnum.EM_ANDAMENTO,
					StatusReclamacaoSugestaoEnum.INDEFERIDA);
		}

		return Arrays.asList(StatusReclamacaoSugestaoEnum.CONCLUIDA);
	}

	public boolean exibeBotaoAtualizarParaCidadao() {
		UsuarioEntidade usuario = SessaoUtil.obterUsuarioLogado();
		return TipoUsuarioEnum.AGENTE.igual(usuario)
				&& StatusReclamacaoSugestaoEnum.ABERTA.diferente(reclamacaoSugestaoRegistro)
				&& StringUtils.isBlank(reclamacaoSugestaoRegistro.getComentarioAgente())
				&& reclamacaoSugestaoRegistro.getCategoria().equals(usuario.getSetor())
				&& reclamacaoSugestaoRegistro.getAgente().getCpf().equals(usuario.getCpf());
	}

	public void atribuirReclamacaoOuSugestaoParaAgente() {
		reclamacaoSugestaoRegra.atribuirReclamacaoOuSugestaoParaAgente(this.reclamacaoSugestaoRegistro, SessaoUtil.obterUsuarioLogado());
		usuarioRegras.marcarParaMostrarNotificacao(reclamacaoSugestaoRegistro.getCidadao());
		TelaUtil.adicionarMensagemDeInformacao(MensagemEnum.DADOS_ATUALIZADOS_SUCESSO);
	}

	public boolean reclamaoOuSugestaoEmAnaliseOuAndamento() {
		UsuarioEntidade usuario = SessaoUtil.obterUsuarioLogado();
		return TipoUsuarioEnum.AGENTE.igual(usuario)
				&& (StatusReclamacaoSugestaoEnum.EM_ANALISE.igual(reclamacaoSugestaoRegistro)
						|| StatusReclamacaoSugestaoEnum.EM_ANDAMENTO.igual(reclamacaoSugestaoRegistro))
				&& reclamacaoSugestaoRegistro.getCategoria().equals(usuario.getSetor())
				&& reclamacaoSugestaoRegistro.getAgente().getCpf().equals(usuario.getCpf());
	}

	public boolean agenteAindaNaoComentou() {
		return StringUtils.isBlank(reclamacaoSugestaoRegistro.getComentarioAgente())
				&& reclamaoSugestaoIndeferidaOuConcluida();
	}

	public boolean reclamaoSugestaoIndeferidaOuConcluida() {
		return StatusReclamacaoSugestaoEnum.INDEFERIDA.igual(reclamacaoSugestaoRegistro)
				|| StatusReclamacaoSugestaoEnum.CONCLUIDA.igual(reclamacaoSugestaoRegistro);
	}

	public boolean agentePodeAtribuirReclamacaoOuSugestaoParaSi() {
		UsuarioEntidade usuario = SessaoUtil.obterUsuarioLogado();
		return TipoUsuarioEnum.AGENTE.igual(usuario)
				&& StatusReclamacaoSugestaoEnum.ABERTA.igual(reclamacaoSugestaoRegistro)
				&& reclamacaoSugestaoRegistro.getCategoria().equals(usuario.getSetor());
	}
	
	public boolean cidadaoPodeIncluirNotaEComentario() {
		UsuarioEntidade usuario = SessaoUtil.obterUsuarioLogado();
		return TipoUsuarioEnum.CIDADAO.igual(usuario)
				&& reclamacaoSugestaoRegistro.getCidadao().getCpf().equals(usuario.getCpf())
				&& (StatusReclamacaoSugestaoEnum.CONCLUIDA.igual(reclamacaoSugestaoRegistro)
						|| StatusReclamacaoSugestaoEnum.INDEFERIDA.igual(reclamacaoSugestaoRegistro))
				&& reclamacaoSugestaoRegistro.getNota() == null;
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