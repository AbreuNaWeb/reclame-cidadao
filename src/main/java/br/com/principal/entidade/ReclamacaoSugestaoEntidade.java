package br.com.principal.entidade;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sun.istack.NotNull;

import br.com.principal.constante.CategoriasEnum;
import br.com.principal.constante.TipoReclamacaoSugestaoEnum;

@Entity
public class ReclamacaoSugestaoEntidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@NotNull
	@Column(name = "TIPO")
	private String tipo;
	
	@NotNull
	@Column(name = "DATA_CRIACAO")
	private Calendar dataCriacao;
	
	@Column(name = "DATA_ATUALIZACAO")
	private Calendar dataAtualizacao;
	
	@Column(name = "DATA_ENCERRAMENTO")
	private Calendar dataEncerramento;
	
	@NotNull
	@Column(name = "TITULO")
	private String titulo;
	
	@NotNull
	@Column(name = "DESCRICAO")
	private String descricao;
	
	@Column(name = "STATUS")
	private Byte status;
	
	@NotNull
	@Column(name = "CATEGORIA")
	private String categoria; 
	
	public ReclamacaoSugestaoEntidade() {
		this.tipo = TipoReclamacaoSugestaoEnum.RECLAMACAO.getDescricao();
		this.categoria = CategoriasEnum.TRANSPORTE_PUBLICO.getDescricao();
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

	public Calendar getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Calendar dataCriacao) {
		this.dataCriacao = dataCriacao;
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
}