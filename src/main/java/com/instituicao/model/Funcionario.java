package com.instituicao.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.instituicao.model.dto.FuncionarioDTO;

@Entity
public class Funcionario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigo;

	@Column(length = 8, nullable = false)
	private String matricula;

	@Column(length = 70, nullable = false)
	private String nome;

	@Column(length = 11, nullable = false)
	private String cpf;

	@Column(nullable = true)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataNascimento;

	@ManyToOne(cascade = CascadeType.MERGE)
	private Cargo cargo;

	@ManyToOne(cascade = CascadeType.MERGE)
	private Empresa empresa;

	
	public Funcionario() {
		super();
	}
	
	public Funcionario(FuncionarioDTO funcionarioDTO) {
		super();
		this.matricula = funcionarioDTO.getMatricula();
		this.nome = funcionarioDTO.getNome();
		this.cpf = funcionarioDTO.getCpf();
		this.dataNascimento = funcionarioDTO.getDataNascimento();
	}


	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	
}
