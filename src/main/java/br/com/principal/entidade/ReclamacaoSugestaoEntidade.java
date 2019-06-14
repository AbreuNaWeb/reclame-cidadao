package br.com.principal.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.principal.constante.CategoriasEnum;
import br.com.principal.constante.StatusReclamacaoSugestaoEnum;
import br.com.principal.constante.TipoReclamacaoSugestaoEnum;
import br.com.principal.tela.util.TelaUtil;

@Table(name = "ReclamacaoSugestao")
@Entity
public class ReclamacaoSugestaoEntidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "TIPO")
	private String tipo;

	@Column(name = "DATA_CRIACAO")
	private String dataCriacao;

	@Column(name = "HORA_CRIACAO")
	private String horaCriacao;
	
	@Column(name = "DATA_ATUALIZACAO")
	private String dataAtualizacao;
	
	@Column(name = "HORA_ATUALIZACAO")
	private String horaAtualizacao;
	
	@Column(name = "DATA_ENCERRAMENTO")
	private String dataEncerramento;

	@Column(name = "TITULO")
	private String titulo;

	@Column(name = "DESCRICAO", length = 300)
	private String descricao;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "CATEGORIA")
	private String categoria;
	
	@Column(name = "COMENTARIO_AGENTE")
	private String comentarioAgente;
	
	@Column(name = "NOTA_CIDADAO")
	private Integer nota;
	
	@Column(name = "FEEDBACK_CIDADAO")
	private String feedbackCidadao;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ENDERECO", foreignKey = @ForeignKey(name = "FK_ENDERECO"))
	private EnderecoEntidade endereco;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CPF_AGENTE", foreignKey = @ForeignKey(name = "FK_AGENTE"))
	private UsuarioEntidade agente;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CPF_CIDADAO", foreignKey = @ForeignKey(name = "FK_CIDADAO"))
	private UsuarioEntidade cidadao;

	public ReclamacaoSugestaoEntidade() {
		this.tipo = TipoReclamacaoSugestaoEnum.RECLAMACAO.getDescricao();
		this.dataCriacao = TelaUtil.diaAtualEmFormatoDiaMesAno();
		this.dataAtualizacao = TelaUtil.diaAtualEmFormatoDiaMesAno();
		this.status = StatusReclamacaoSugestaoEnum.ABERTA.getDescricao();
		this.categoria = CategoriasEnum.AGUA_ESGOTO.getDescricao();
		this.endereco = new EnderecoEntidade();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(String dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getHoraCriacao() {
		return horaCriacao;
	}

	public void setHoraCriacao(String horaCriacao) {
		this.horaCriacao = horaCriacao;
	}

	public String getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(String dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
	
	public String getHoraAtualizacao() {
		return horaAtualizacao;
	}
	
	public void setHoraAtualizacao(String horaAtualizacao) {
		this.horaAtualizacao = horaAtualizacao;
	}

	public String getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(String dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public String getComentarioAgente() {
		return comentarioAgente;
	}
	
	public void setComentarioAgente(String comentarioAgente) {
		this.comentarioAgente = comentarioAgente;
	}
	
	public Integer getNota() {
		return nota;
	}
	
	public void setNota(Integer nota) {
		this.nota = nota;
	}
	
	public String getFeedbackCidadao() {
		return feedbackCidadao;
	}
	
	public void setFeedbackCidadao(String feedbackCidadao) {
		this.feedbackCidadao = feedbackCidadao;
	}

	public EnderecoEntidade getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoEntidade endereco) {
		this.endereco = endereco;
	}

	public UsuarioEntidade getAgente() {
		return agente;
	}

	public void setAgente(UsuarioEntidade agente) {
		this.agente = agente;
	}

	public UsuarioEntidade getCidadao() {
		return cidadao;
	}

	public void setCidadao(UsuarioEntidade cidadao) {
		this.cidadao = cidadao;
	}
}