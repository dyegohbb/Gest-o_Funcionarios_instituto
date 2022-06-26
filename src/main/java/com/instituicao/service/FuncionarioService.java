package com.instituicao.service;

import java.util.List;

import com.instituicao.model.dto.FuncionarioDTO;

public interface FuncionarioService {

	public List<FuncionarioDTO> listAll() throws Exception;
	
	public FuncionarioDTO findById(Integer id) throws Exception;
	
	public String insert(FuncionarioDTO funcionarioDTO) throws Exception;
	
	public String update(Integer id, FuncionarioDTO funcionarioDTO) throws Exception;
	
	public String delete(Integer id) throws Exception;
	
	public List<FuncionarioDTO> listByEmpresaId(Integer id) throws Exception;
	
	public List<FuncionarioDTO> listByCargoId(Integer id) throws Exception;
}
