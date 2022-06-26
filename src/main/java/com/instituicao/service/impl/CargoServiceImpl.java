package com.instituicao.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.instituicao.model.Cargo;
import com.instituicao.model.dao.CargoDAO;
import com.instituicao.model.dto.CargoDTO;
import com.instituicao.model.dto.FuncionarioDTO;
import com.instituicao.service.CargoService;
import com.instituicao.service.FuncionarioService;

@Service
public class CargoServiceImpl implements CargoService {

	@Autowired
	private CargoDAO cargoDAO;

	@Autowired
	private FuncionarioService funcionarioService;

	@Transactional
	@Override
	public List<CargoDTO> listAll() throws Exception {
		List<Cargo> cargos = new ArrayList<Cargo>();
		List<CargoDTO> cargosDTO = new ArrayList<CargoDTO>();
		try {
			cargos = cargoDAO.findAll(Sort.by(Sort.Direction.ASC, "codigo"));
			for (Cargo cargo : cargos) {
				CargoDTO cargoDTO = new CargoDTO(cargo);
				cargosDTO.add(cargoDTO);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return cargosDTO;
	}

	@Transactional
	@Override
	public CargoDTO findById(Integer id) throws Exception {
		Cargo cargo = new Cargo();
		CargoDTO cargoDTO = new CargoDTO();
		try {
			cargo = cargoDAO.findById(id).orElse(null);
			if (cargo != null) {
				cargoDTO = new CargoDTO(cargo);
			} else {
				cargoDTO = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return cargoDTO;
	}

	@Transactional
	@Override
	public String insert(Cargo cargo) throws Exception {
		String situacao;

		var matcher = ExampleMatcher.matching().withMatcher("descricao", match -> match.endsWith())
				.withIgnorePaths("codigo", "descricaoAbreviada", "ativo");

		Example<Cargo> cargoExample = Example.of(cargo, matcher);
		if (cargoDAO.exists(cargoExample)) {
			situacao = "Não salvo, MOTIVO: cargo Existente.";
		} else {
			try {
				cargoDAO.save(cargo);
				situacao = "Salvo com sucesso!";
			} catch (Exception e) {
				situacao = "Não salvo, MOTIVO: verificar com o suporte.";
				e.printStackTrace();
			}
		}

		return situacao;
	}

	@Transactional
	@Override
	public String update(Integer id, CargoDTO cargoDTO) throws Exception {
		String situacao;
		Cargo cargo = cargoDAO.findById(id).orElse(null);
		if (cargo == null) {
			situacao = "Não atualizada, MOTIVO: cargo não existente";
		} else {
			cargo.setAtivo(cargoDTO.isAtivo());
			cargo.setCodigo(cargoDTO.getCodigo());
			cargo.setDescricao(cargoDTO.getDescricao());
			cargo.setDescricaoAbreviada(cargoDTO.getDescricaoAbreviada());
			try {
				cargoDAO.save(cargo);
				situacao = "Atualizado com sucesso!";
			} catch (Exception e) {
				situacao = "Não atualizado, MOTIVO: verificar com o suporte";
				e.printStackTrace();
			}
		}
		return situacao;
	}

	@Transactional
	@Override
	public String delete(Integer id) throws Exception {
		String situacao;
		Cargo cargo = cargoDAO.findById(id).orElse(null);
		if (cargo == null) {
			situacao = "Não deletada, MOTIVO: cargo não existente";
		} else {
			if (this.existeVinculo(cargo)) {
				situacao = "Não deletado, MOTIVO: cargo possui vinculo";
			} else {
				try {
					cargoDAO.delete(cargo);
					situacao = "Deletada com sucesso!";
				} catch (Exception e) {
					situacao = "Não atualizado, MOTIVO: verificar com o suporte";
					e.printStackTrace();
				}
			}
		}
	return situacao;

	}

	@Transactional
	@Override
	public Cargo fromDTO(CargoDTO cargoDTO) {
		return new Cargo(cargoDTO);
	}

	@Transactional
	@Override
	public boolean existeVinculo(Cargo cargo) throws Exception {
		List<FuncionarioDTO> funcionarios = new ArrayList<FuncionarioDTO>();
		try {
			funcionarios = this.funcionarioService.listByEmpresaId(cargo.getCodigo());
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (!funcionarios.isEmpty()) {
			return true;
		}
		return false;

	}

	@Transactional
	@Override
	public List<CargoDTO> searchByName(String name) throws Exception {
		List<Cargo> cargos = new ArrayList<Cargo>();
		List<CargoDTO> cargosDTO = new ArrayList<CargoDTO>();
		try {
			cargos = cargoDAO.searchByName(name);
			for (Cargo cargo : cargos) {
				CargoDTO cargoDTO = new CargoDTO(cargo);
				cargosDTO.add(cargoDTO);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return cargosDTO;
	}
}
