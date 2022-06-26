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

import com.instituicao.model.Cargo;
import com.instituicao.model.dto.CargoDTO;
import com.instituicao.service.CargoService;

@RestController
@RequestMapping(value = "/cargos")
public class CargoController {

	@Autowired
	private CargoService cargoService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CargoDTO>> listAll() throws Exception {

		List<CargoDTO> cargos = this.cargoService.listAll();
		if (cargos.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok().body(cargos);
		}

	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ResponseEntity<List<CargoDTO>> listSearch(@RequestParam String name) throws Exception {

		List<CargoDTO> cargos = this.cargoService.searchByName(name);
		if (cargos.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok().body(cargos);
		}

	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<CargoDTO> findById(@PathVariable Integer id) throws Exception {

		CargoDTO cargo = this.cargoService.findById(id);
		if (cargo == null) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok().body(cargo);
		}

	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ResponseEntity<String> insert(@RequestBody Cargo cargo) throws Exception {
		String situacao = this.cargoService.insert(cargo);
		if (situacao.equals("Não salvo, MOTIVO: cargo Existente.")) {
			return ResponseEntity.badRequest().body(situacao);
		} else if (situacao.equals("Não salvo, MOTIVO: verificar com o suporte.")) {
			return ResponseEntity.badRequest().body(situacao);
		} else {
			return ResponseEntity.ok().body(situacao);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody CargoDTO cargoDTO)
			throws Exception {
		String situacao = this.cargoService.update(id, cargoDTO);
		if (situacao.equals("Não atualizada, MOTIVO: cargo não existente")) {
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
		String situacao = this.cargoService.delete(id);
		if (situacao.equals("Não deletada, MOTIVO: cargo não existente")) {
			return ResponseEntity.notFound().build();
		} else if (situacao.equals("Não atualizado, MOTIVO: verificar com o suporte")) {
			return ResponseEntity.badRequest().body(situacao);
		} else if (situacao.equals("Não deletado, MOTIVO: cargo possui vinculo")) {
			return ResponseEntity.badRequest().body(situacao);
		}else {
			return ResponseEntity.ok().body(situacao);
		}
	}
	
}
