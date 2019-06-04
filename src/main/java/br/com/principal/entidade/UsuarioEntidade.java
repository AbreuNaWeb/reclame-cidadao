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
	private Long dddCelular;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "SETOR")
	private String setor;

	@Column(name = "DATA_CADASTRO")
	private String dataCadastro;

	@Transient
	private String emailConfirmado;

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

	public Long getDddCelular() {
		return dddCelular;
	}

	public void setDddCelular(Long dddCelular) {
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

	public String getEmailConfirmado() {
		return emailConfirmado;
	}

	public void setEmailConfirmado(String emailConfirmado) {
		this.emailConfirmado = emailConfirmado;
	}

	public String getSenhaConfirmada() {
		return senhaConfirmada;
	}

	public void setSenhaConfirmada(String senhaConfirmada) {
		this.senhaConfirmada = senhaConfirmada;
	}
}