package com.instituicao.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.instituicao.model.Funcionario;

public interface FuncionarioDAO extends JpaRepository<Funcionario, Integer>{

	@Query(value = "SELECT * FROM FUNCIONARIO WHERE EMPRESA_CODIGO = :id", nativeQuery = true)
	List<Funcionario> listByEmpresaId(Integer id);
	
	@Query(value = "SELECT * FROM FUNCIONARIO WHERE CARGO_CODIGO = :id", nativeQuery = true)
	List<Funcionario> listByCargoId(Integer id);
	
}
