package br.com.sistema.escolar.rest;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sistema.escolar.entity.EntidadeDominio;
import br.com.sistema.escolar.entity.Materia;
import br.com.sistema.escolar.services.MateriaServices;

@CrossOrigin("*")
@RestController
@RequestMapping("/materia")
public class MateriaRest {

	private MateriaServices services = new MateriaServices();
	
	@PostMapping//(produces = MediaType.APPLICATION_JSON_VALUE)
	public EntidadeDominio create(@RequestBody Materia materia) {
		return services.salvar(materia);
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EntidadeDominio> findById(@PathVariable("id") Integer id) throws Exception {
		return ResponseEntity.ok(services.consultar(id));
	}
	
	@DeleteMapping(path = { "/{id}" })
	public void delete(@PathVariable Integer id) {
		services.excluir(id);
	}
	
	@GetMapping(value = "/listar")
	public ResponseEntity<List<EntidadeDominio>> findAll() {
		return ResponseEntity.ok(services.listar());
	}
}
