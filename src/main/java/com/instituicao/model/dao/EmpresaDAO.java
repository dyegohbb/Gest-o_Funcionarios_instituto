package com.instituicao.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.instituicao.model.Empresa;

public interface EmpresaDAO extends JpaRepository<Empresa, Integer> {

	@Query(value = "SELECT * FROM EMPRESA WHERE NOME LIKE CONCAT('%',:name,'%')", nativeQuery = true)
	List<Empresa> searchByName(String name);
}
