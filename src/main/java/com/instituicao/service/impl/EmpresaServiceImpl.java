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
import com.instituicao.model.Empresa;
import com.instituicao.model.dao.EmpresaDAO;
import com.instituicao.model.dto.CargoDTO;
import com.instituicao.model.dto.EmpresaDTO;
import com.instituicao.model.dto.FuncionarioDTO;
import com.instituicao.service.EmpresaService;
import com.instituicao.service.FuncionarioService;

@Service
public class EmpresaServiceImpl implements EmpresaService {

	@Autowired
	private EmpresaDAO empresaDAO;
	
	@Autowired
	private FuncionarioService funcionarioService;

	@Transactional
	@Override
	public List<EmpresaDTO> listAll() throws Exception {
		List<Empresa> empresas = new ArrayList<Empresa>();
		List<EmpresaDTO> empresasDTO = new ArrayList<EmpresaDTO>();
		try {
			empresas = empresaDAO.findAll(Sort.by(Sort.Direction.ASC, "codigo"));
			for (Empresa empresa : empresas) {
				EmpresaDTO empresaDTO = new EmpresaDTO(empresa);
				empresasDTO.add(empresaDTO);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return empresasDTO;
	}

	@Transactional
	@Override
	public EmpresaDTO findById(Integer id) throws Exception {
		Empresa empresa = new Empresa();
		EmpresaDTO empresaDTO = new EmpresaDTO();
		try {
			empresa = empresaDAO.findById(id).orElse(null);
			if (empresa != null) {
				empresaDTO = new EmpresaDTO(empresa);
			} else {
				empresaDTO = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return empresaDTO;
	}

	@Transactional
	@Override
	public String insert(Empresa empresa) throws Exception {
		String situacao;

		var matcher = ExampleMatcher.matching().withMatcher("nome", match -> match.endsWith()).withIgnorePaths("codigo",
				"nomeAbreviado", "email", "principal", "ativo");

		Example<Empresa> empresaExample = Example.of(empresa, matcher);
		if (empresaDAO.exists(empresaExample)) {
			situacao = "Não salvo, MOTIVO: empresa Existente.";
		} else {
			try {
				empresaDAO.save(empresa);
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
	public String update(Integer id, EmpresaDTO empresaDTO) throws Exception {
		String situacao;
		Empresa empresa = empresaDAO.findById(id).orElse(null);
		if (empresa == null) {
			situacao = "Não atualizada, MOTIVO: empresa não existente";
		} else {
			empresa.setAtivo(empresaDTO.isAtivo());
			empresa.setEmail(empresaDTO.getEmail());
			empresa.setNome(empresaDTO.getNome());
			empresa.setNomeAbreviado(empresaDTO.getNomeAbreviado());
			empresa.setPrincipal(empresaDTO.isPrincipal());
			try {
				empresaDAO.save(empresa);
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
		Empresa empresa = empresaDAO.findById(id).orElse(null);
		
		if (empresa == null) {
			situacao = "Não deletada, MOTIVO: empresa não existente";
		} else {
			if(this.existeVinculo(empresa)) {
				situacao = "Não deletada, MOTIVO: empresa possui vinculo";
			}else {
				try {
					empresaDAO.delete(empresa);
					situacao = "Deletada com sucesso!";
				}catch (Exception e) {
					situacao = "Não atualizado, MOTIVO: verificar com o suporte";
					e.printStackTrace();
				}
			}
		}
		return situacao;
	}

	@Transactional
	@Override
	public Empresa fromDTO(EmpresaDTO empresaDTO) {
		return new Empresa(empresaDTO);
	}
	
	@Transactional
	@Override
	public boolean existeVinculo(Empresa empresa) throws Exception {
		List<FuncionarioDTO> funcionarios = new ArrayList<FuncionarioDTO>();
		try {
			funcionarios = this.funcionarioService.listByEmpresaId(empresa.getCodigo());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(!funcionarios.isEmpty()) {
			return true;
		}
		return false;
	}
	
	@Transactional
	@Override
	public List<EmpresaDTO> searchByName(String name) throws Exception {
		List<Empresa> empresas = new ArrayList<Empresa>();
		List<EmpresaDTO> empresasDTO = new ArrayList<EmpresaDTO>();
		try {
			empresas = empresaDAO.searchByName(name);
			for (Empresa cargo : empresas) {
				EmpresaDTO empresaDTO = new EmpresaDTO(cargo);
				empresasDTO.add(empresaDTO);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return empresasDTO;
	}
	
}
