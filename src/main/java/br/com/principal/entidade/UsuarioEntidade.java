package br.com.principal.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "Usuario")
@Entity
public class UsuarioEntidade implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CPF")
	private Long cpf;

	@Column(name = "NOME")
	private String nome;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "SENHA")
	private String senha;

	@Column(name = "TIPO")
	private String tipo;

	@Column(name = "DDD_CELULAR")
	private String dddCelular;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "SETOR")
	private String setor;

	@Column(name = "DATA_CADASTRO")
	private String dataCadastro;
	
	@Column(name = "MOTIVO_BLOQUEIO")
	private String motivoBloqueio;
	
	@Column(name = "MOSTRAR_NOTIFICACAO")
	private String mostrarNotificacao;
	
	@Transient
	private String senhaConfirmada;

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDddCelular() {
		return dddCelular;
	}

	public void setDddCelular(String dddCelular) {
		this.dddCelular = dddCelular;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	public String getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	public String getSenhaConfirmada() {
		return senhaConfirmada;
	}

	public void setSenhaConfirmada(String senhaConfirmada) {
		this.senhaConfirmada = senhaConfirmada;
	}
	
	public String getMotivoBloqueio() {
		return motivoBloqueio;
	}
	
	public void setMotivoBloqueio(String motivoBloqueio) {
		this.motivoBloqueio = motivoBloqueio;
	}
	
	public String getMostrarNotificacao() {
		return mostrarNotificacao;
	}
	
	public void setMostrarNotificacao(String mostrarNotificacao) {
		this.mostrarNotificacao = mostrarNotificacao;
	}
}