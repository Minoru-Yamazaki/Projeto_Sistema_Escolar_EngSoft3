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
import br.com.sistema.escolar.entity.Professor;
import br.com.sistema.escolar.services.ProfessorServices;

@CrossOrigin("*")
@RestController
@RequestMapping("/professor")
public class ProfessorRest {

	private ProfessorServices services = new ProfessorServices();

	@PostMapping
	public EntidadeDominio create(@RequestBody Professor professor) {
		return services.salvar(professor);
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EntidadeDominio> findById(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(services.consultar(id));
	}
	
	@GetMapping(value = "/listar")
	public List<EntidadeDominio> findAll() {
		return services.listar();
	}
	
	@DeleteMapping(path = { "/{id}" })
	public void delete(@PathVariable Integer id) {
		services.excluir(id);
	}
}
