package br.com.principal.entidade;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.principal.constante.CategoriasEnum;
import br.com.principal.constante.TipoReclamacaoSugestaoEnum;
import br.com.principal.tela.TelaUtil;

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
	private Calendar dataAtualizacao;

	@Column(name = "DATA_ENCERRAMENTO")
	private Calendar dataEncerramento;

	@Column(name = "TITULO")
	private String titulo;

	@Column(name = "DESCRICAO")
	private String descricao;

	@Column(name = "STATUS")
	private Byte status;

	@Column(name = "CATEGORIA")
	private String categoria;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_endereco")
	private EnderecoEntidade endereco;

	public ReclamacaoSugestaoEntidade() {
		this.tipo = TipoReclamacaoSugestaoEnum.RECLAMACAO.getDescricao();
		this.dataCriacao = TelaUtil.converterCalendarParaDiaMesAno(Calendar.getInstance());
		this.categoria = CategoriasEnum.TRANSPORTE_PUBLICO.getDescricao();
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

	public Calendar getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Calendar dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public Calendar getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(Calendar dataEncerramento) {
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

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public EnderecoEntidade getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoEntidade endereco) {
		this.endereco = endereco;
	}
}