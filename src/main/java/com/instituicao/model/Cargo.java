package com.instituicao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.instituicao.model.dto.CargoDTO;

@Entity
public class Cargo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigo;
	
	@Column(length = 70, nullable = false)
	private String descricao;
	
	@Column(length = 15, nullable = true)
	private String descricaoAbreviada;
	
	@Column(nullable = true)
	private boolean ativo = true;

	public Cargo() {
		
	}
	
	public Cargo(CargoDTO cargoDTO) {
		this.codigo = cargoDTO.getCodigo();
		this.descricao = cargoDTO.getDescricao();
		this.descricaoAbreviada = cargoDTO.getDescricaoAbreviada();
		this.ativo = cargoDTO.isAtivo();
	}



	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
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

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	
}
