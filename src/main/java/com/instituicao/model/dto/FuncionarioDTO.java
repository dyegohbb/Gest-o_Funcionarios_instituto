package com.instituicao.model.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.instituicao.model.Funcionario;

public class FuncionarioDTO implements Serializable {

	private static final long serialVersionUID = -3326244804641212473L;

	private Integer codigo;
	
	@NotEmpty(message = "Campo matricula é obrigatório")
	@Length(max = 8, message = "Campo matricula limitado a 8 caracteres")
	private String matricula;

	@NotEmpty(message = "Campo nome é obrigatório")
	@Length(max = 70, message = "Campo nome limitado a 70 caracteres")
	private String nome;

	@NotEmpty(message = "Campo cpf é obrigatório")
	@Length(max = 11, message = "Campo cpf limitado a 11 caracteres")
	private String cpf;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataNascimento;
	
	private Integer cargoId;

	private Integer empresaId;

	public FuncionarioDTO(Funcionario funcionario) {
		super();
		this.codigo = funcionario.getCodigo();
		this.matricula = funcionario.getMatricula();
		this.nome = funcionario.getNome();
		this.cpf = funcionario.getCpf();
		this.dataNascimento = funcionario.getDataNascimento();
		this.cargoId = funcionario.getCargo().getCodigo();
		this.empresaId = funcionario.getEmpresa().getCodigo();
	}

	public FuncionarioDTO() {
		super();
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCargoId() {
		return cargoId;
	}

	public void setCargoId(Integer cargoId) {
		this.cargoId = cargoId;
	}

	public Integer getEmpresaId() {
		return empresaId;
	}

	public void setEmpresaId(Integer empresaId) {
		this.empresaId = empresaId;
	}

}
