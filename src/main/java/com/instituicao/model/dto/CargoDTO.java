package com.instituicao.model.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.instituicao.model.Cargo;

public class CargoDTO implements Serializable {

	private static final long serialVersionUID = -7695715006115702713L;

	private Integer codigo;
	
	@NotEmpty(message="Campo descrição é obrigatório")
	@Length(max=70, message="Campo descrição limitado a 70 caracteres")
	private String descricao;
	
	@Length(max=15, message="Campo descrição abreviada limitado a 70 caracteres")
	private String descricaoAbreviada;
	
	private boolean ativo = true;

	public CargoDTO(Cargo cargo) {
		super();
		this.codigo = cargo.getCodigo();
		this.descricao = cargo.getDescricao();
		this.descricaoAbreviada = cargo.getDescricaoAbreviada();
		this.ativo = cargo.isAtivo();
	}

	public CargoDTO() {
		super();
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
}
