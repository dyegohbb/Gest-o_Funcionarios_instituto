package com.instituicao.service;

import java.util.List;

import com.instituicao.model.Cargo;
import com.instituicao.model.dto.CargoDTO;

public interface CargoService {

	public List<CargoDTO> listAll() throws Exception;
	
	public List<CargoDTO> searchByName(String name) throws Exception;
	
	public CargoDTO findById(Integer id) throws Exception;
	
	public String insert(Cargo cargo) throws Exception;
	
	public String update(Integer id, CargoDTO cargoDTO) throws Exception;
	
	public String delete(Integer id) throws Exception;

	public Cargo fromDTO(CargoDTO cargoDTO);

	boolean existeVinculo(Cargo cargo) throws Exception;
}
