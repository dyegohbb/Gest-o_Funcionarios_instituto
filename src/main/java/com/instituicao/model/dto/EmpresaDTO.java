package com.instituicao.model.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.instituicao.model.Empresa;

public class EmpresaDTO implements Serializable {

	private static final long serialVersionUID = -1164288140862727521L;

	private Integer codigo;
	
	@NotEmpty(message = "Campo descrição é obrigatório")
	@Length(max = 50, message = "Campo nome limitado a 50 caracteres")
	private String nome;

	@Length(max = 10, message = "Campo nome abreviado limitado a 10 caracteres")
	private String nomeAbreviado;

	@Length(max = 80, message = "Campo E-mail a 80 caracteres")
	private String email;
	
	@NotEmpty(message = "Campo empresa principal é obrigatório")
	private boolean principal; 
	
	@Column(nullable = true)
	private boolean ativo = true;
	
	public EmpresaDTO() {
		super();
	}

	public EmpresaDTO(Empresa empresa) {
		super();
		this.codigo = empresa.getCodigo();
		this.nome = empresa.getNome();
		this.nomeAbreviado = empresa.getNomeAbreviado();
		this.email = empresa.getEmail();
		this.principal = empresa.isPrincipal();
		this.ativo = empresa.isAtivo();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeAbreviado() {
		return nomeAbreviado;
	}

	public void setNomeAbreviado(String nomeAbreviado) {
		this.nomeAbreviado = nomeAbreviado;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isPrincipal() {
		return principal;
	}

	public void setPrincipal(boolean principal) {
		this.principal = principal;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

}
