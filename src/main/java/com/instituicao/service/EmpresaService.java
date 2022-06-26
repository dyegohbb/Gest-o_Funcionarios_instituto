package com.instituicao.service;

import java.util.List;

import com.instituicao.model.Empresa;
import com.instituicao.model.dto.CargoDTO;
import com.instituicao.model.dto.EmpresaDTO;

public interface EmpresaService {

	public List<EmpresaDTO> listAll() throws Exception;
	
	public List<EmpresaDTO> searchByName(String name) throws Exception;
	
	public EmpresaDTO findById(Integer id) throws Exception;
	
	public String insert(Empresa empresa) throws Exception;
	
	public String update(Integer id, EmpresaDTO empresaDTO) throws Exception;
	
	public String delete(Integer id) throws Exception;
	
	public Empresa fromDTO(EmpresaDTO empresaDTO);
	
	public boolean existeVinculo(Empresa empresa) throws Exception; 
}
