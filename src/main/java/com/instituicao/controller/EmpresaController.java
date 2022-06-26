package com.instituicao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.instituicao.model.Empresa;
import com.instituicao.model.dto.CargoDTO;
import com.instituicao.model.dto.EmpresaDTO;
import com.instituicao.service.EmpresaService;

@RestController
@RequestMapping(value = "/empresas")
public class EmpresaController {

	@Autowired
	private EmpresaService empresaService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<EmpresaDTO>> listAll() throws Exception {

		List<EmpresaDTO> empresas = this.empresaService.listAll();
		if (empresas.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok().body(empresas);
		}

	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ResponseEntity<List<EmpresaDTO>> listSearch(@RequestParam String name) throws Exception {

		List<EmpresaDTO> empresas = this.empresaService.searchByName(name);
		if (empresas.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok().body(empresas);
		}

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<EmpresaDTO> findById(@PathVariable Integer id) throws Exception {

		EmpresaDTO empresa = this.empresaService.findById(id);
		if (empresa == null) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok().body(empresa);
		}

	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ResponseEntity<String> insert(@RequestBody Empresa empresa) throws Exception {
		String situacao = this.empresaService.insert(empresa);
		if (situacao.equals("Não salvo, MOTIVO: empresa Existente.")) {
			return ResponseEntity.badRequest().body(situacao);
		} else if (situacao.equals("Não salvo, MOTIVO: verificar com o suporte.")) {
			return ResponseEntity.badRequest().body(situacao);
		} else {
			return ResponseEntity.ok().body(situacao);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody EmpresaDTO empresaDTO)
			throws Exception {
		String situacao = this.empresaService.update(id, empresaDTO);
		if (situacao.equals("Não atualizada, MOTIVO: empresa não existente")) {
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
		String situacao = this.empresaService.delete(id);
		if (situacao.equals("Não deletada, MOTIVO: empresa não existente")) {
			return ResponseEntity.notFound().build();
		} else if (situacao.equals("Não atualizado, MOTIVO: verificar com o suporte")) {
			return ResponseEntity.badRequest().body(situacao);
		} else if (situacao.equals("Não deletada, MOTIVO: empresa possui vinculo")) {
			return ResponseEntity.badRequest().body(situacao);
		}else {
			return ResponseEntity.ok().body(situacao);
		}
	}
	
}
