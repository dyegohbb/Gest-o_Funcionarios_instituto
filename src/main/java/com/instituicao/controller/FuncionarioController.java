package com.instituicao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.instituicao.model.dto.FuncionarioDTO;
import com.instituicao.service.FuncionarioService;

@RestController
@RequestMapping(value = "/funcionarios")
public class FuncionarioController {

	@Autowired
	private FuncionarioService funcionarioService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<FuncionarioDTO>> listAll() throws Exception {

		List<FuncionarioDTO> funcionarios = this.funcionarioService.listAll();
		if (funcionarios.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok().body(funcionarios);
		}

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<FuncionarioDTO> findById(@PathVariable Integer id) throws Exception {

		FuncionarioDTO funcionario = this.funcionarioService.findById(id);
		if (funcionario == null) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok().body(funcionario);
		}

	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ResponseEntity<String> insert(@RequestBody FuncionarioDTO funcionarioDTO ) throws Exception {
		String situacao = this.funcionarioService.insert(funcionarioDTO);
		if (situacao.equals("Não salvo, MOTIVO: funcionario Existente.")) {
			return ResponseEntity.badRequest().body(situacao);
		} else if (situacao.equals("Não salvo, MOTIVO: verificar com o suporte.")) {
			return ResponseEntity.badRequest().body(situacao);
		} else {
			return ResponseEntity.ok().body(situacao);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody FuncionarioDTO funcionarioDTO)
			throws Exception {
		String situacao = this.funcionarioService.update(id, funcionarioDTO);
		if (situacao.equals("Não atualizada, MOTIVO: funcionario não existente")) {
			return ResponseEntity.notFound().build();
		} else if (situacao.equals("Não atualizado, MOTIVO: verificar com o suporte")) {
			return ResponseEntity.badRequest().body(situacao);
		} else {
			return ResponseEntity.ok().body(situacao);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable Integer id)
			throws Exception {
		String situacao = this.funcionarioService.delete(id);
		if (situacao.equals("Não deletada, MOTIVO: funcionario não existente")) {
			return ResponseEntity.notFound().build();
		} else if (situacao.equals("Não atualizado, MOTIVO: verificar com o suporte")) {
			return ResponseEntity.badRequest().body(situacao);
		} else {
			return ResponseEntity.ok().body(situacao);
		}
	}
}
