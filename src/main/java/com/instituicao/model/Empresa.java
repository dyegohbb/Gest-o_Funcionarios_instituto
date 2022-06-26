package com.instituicao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.instituicao.model.dto.EmpresaDTO;

@Entity
public class Empresa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigo;
	
	@Column(length = 50, nullable = false)
	private String nome;
	
	@Column(length = 10, nullable = true)
	private String nomeAbreviado;
	
	@Column(length = 80, nullable = true)
	private String email;
	
	@Column(nullable = false)
	private boolean principal; 
	
	@Column(nullable = true)
	private boolean ativo = true;

	public Empresa(EmpresaDTO empresaDTO) {
		this.codigo = empresaDTO.getCodigo();
		this.nome = empresaDTO.getNome();
		this.nomeAbreviado = empresaDTO.getNomeAbreviado();
		this.email = empresaDTO.getEmail();
		this.principal = empresaDTO.isPrincipal();
		this.ativo = empresaDTO.isAtivo();
	}

	public Empresa() {
		// TODO Auto-generated constructor stub
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
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

}
