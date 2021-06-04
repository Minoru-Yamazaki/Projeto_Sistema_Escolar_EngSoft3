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

import br.com.sistema.escolar.entity.Aluno;
import br.com.sistema.escolar.entity.EntidadeDominio;
import br.com.sistema.escolar.services.AlunoServices;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin("*")
@RestController
@RequestMapping("/aluno")
public class AlunoRest {

	private AlunoServices services = new AlunoServices();

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Aluno> findById(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(services.consultar(id));
	}
	
	@PostMapping
	public Aluno create(@RequestBody Aluno aluno) {
		return (Aluno)services.salvar(aluno);
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
