package com.instituicao.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.instituicao.model.Cargo;

public interface CargoDAO extends JpaRepository<Cargo, Integer>{

	@Query(value = "SELECT * FROM CARGO WHERE DESCRICAO LIKE CONCAT('%',:name,'%')", nativeQuery = true)
	List<Cargo> searchByName(String name);
	
}
