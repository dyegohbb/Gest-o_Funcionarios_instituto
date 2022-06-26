package com.instituicao.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.instituicao.model.Funcionario;
import com.instituicao.model.dao.FuncionarioDAO;
import com.instituicao.model.dto.FuncionarioDTO;
import com.instituicao.service.CargoService;
import com.instituicao.service.EmpresaService;
import com.instituicao.service.FuncionarioService;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

	@Autowired
	private FuncionarioDAO funcionarioDAO;

	@Autowired
	private EmpresaService empresaService;

	@Autowired
	private CargoService cargoService;

	@Transactional
	@Override
	public List<FuncionarioDTO> listAll() throws Exception {
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		List<FuncionarioDTO> funcionariosDTO = new ArrayList<FuncionarioDTO>();
		try {
			funcionarios = funcionarioDAO.findAll(Sort.by(Sort.Direction.ASC, "codigo"));
			for (Funcionario funcionario : funcionarios) {
				FuncionarioDTO funcionarioDTO = new FuncionarioDTO(funcionario);
				funcionariosDTO.add(funcionarioDTO);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return funcionariosDTO;
	}

	@Transactional
	@Override
	public FuncionarioDTO findById(Integer id) throws Exception {
		Funcionario funcionario = new Funcionario();
		FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
		try {
			funcionario = funcionarioDAO.findById(id).orElse(null);
			if (funcionario != null) {
				funcionarioDTO = new FuncionarioDTO(funcionario);
			} else {
				funcionarioDTO = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return funcionarioDTO;
	}

	@Transactional
	@Override
	public String insert(FuncionarioDTO funcionarioDTO) throws Exception {
		Funcionario funcionario = new Funcionario(funcionarioDTO);
		funcionario.setCargo(this.cargoService.fromDTO(cargoService.findById(funcionarioDTO.getCargoId())));
		funcionario.setEmpresa(this.empresaService.fromDTO(empresaService.findById(funcionarioDTO.getEmpresaId())));

		String situacao;
		var matcher = ExampleMatcher.matching().withMatcher("cpf", match -> match.endsWith()).withIgnorePaths("codigo",
				"dataNascimento", "cargoId", "empresaId", "matricula");
		var matcher2 = ExampleMatcher.matching().withMatcher("matricula", match -> match.endsWith()).withIgnorePaths("codigo",
				"dataNascimento", "cargoId", "empresaId", "cpf");

		Example<Funcionario> funcionarioExample = Example.of(funcionario, matcher);
		Example<Funcionario> funcionarioExample2 = Example.of(funcionario, matcher2);
		if (funcionarioDAO.exists(funcionarioExample) || funcionarioDAO.exists(funcionarioExample2)) {
			situacao = "Não salvo, MOTIVO: funcionario Existente.";
		} else {
			try {
				funcionarioDAO.save(funcionario);
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
	public String update(Integer id, FuncionarioDTO funcionarioDTO) throws Exception {
		String situacao;
		Funcionario funcionario = funcionarioDAO.findById(id).orElse(null);
		if (funcionario == null) {
			situacao = "Não atualizada, MOTIVO: funcionario não existente";
		} else {
			funcionario.setMatricula(funcionarioDTO.getMatricula());
			funcionario.setNome(funcionarioDTO.getNome());
			funcionario.setCpf(funcionarioDTO.getCpf());
			funcionario.setDataNascimento(funcionarioDTO.getDataNascimento());
			funcionario.setCargo(this.cargoService.fromDTO(cargoService.findById(funcionarioDTO.getCargoId())));
			funcionario.setEmpresa(this.empresaService.fromDTO(empresaService.findById(funcionarioDTO.getEmpresaId())));
			try {
				funcionarioDAO.save(funcionario);
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
	public String delete(Integer id) {
		String situacao;
		Funcionario funcionario = funcionarioDAO.findById(id).orElse(null);
		if (funcionario == null) {
			situacao = "Não deletada, MOTIVO: funcionario não existente";
		} else {
			try {
				funcionarioDAO.delete(funcionario);
				situacao = "Deletada com sucesso!";
			} catch (Exception e) {
				situacao = "Não atualizado, MOTIVO: verificar com o suporte";
				e.printStackTrace();
			}
		}
		return situacao;
	}
	
	@Transactional
	@Override
	public List<FuncionarioDTO> listByEmpresaId(Integer id) throws Exception {
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		List<FuncionarioDTO> funcionariosDTO = new ArrayList<FuncionarioDTO>();
		try {
			funcionarios = funcionarioDAO.listByEmpresaId(id);
			for (Funcionario funcionario : funcionarios) {
				FuncionarioDTO funcionarioDTO = new FuncionarioDTO(funcionario);
				funcionariosDTO.add(funcionarioDTO);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return funcionariosDTO;
	}

	@Transactional
	@Override
	public List<FuncionarioDTO> listByCargoId(Integer id) throws Exception {
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		List<FuncionarioDTO> funcionariosDTO = new ArrayList<FuncionarioDTO>();
		try {
			funcionarios = funcionarioDAO.listByCargoId(id);
			for (Funcionario funcionario : funcionarios) {
				FuncionarioDTO funcionarioDTO = new FuncionarioDTO(funcionario);
				funcionariosDTO.add(funcionarioDTO);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return funcionariosDTO;
	}
}
