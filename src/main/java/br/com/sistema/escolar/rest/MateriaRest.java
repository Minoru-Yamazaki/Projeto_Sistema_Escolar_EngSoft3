package br.com.sistema.escolar.rest;

import br.com.sistema.escolar.command.ICommand;
import br.com.sistema.escolar.configuration.CommandOperation;
import br.com.sistema.escolar.entity.EntidadeDominio;
import br.com.sistema.escolar.services.MateriaServices;
import br.com.sistema.escolar.web.domain.EntidadeNegocio;
import br.com.sistema.escolar.web.domain.dto.MateriaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static br.com.sistema.escolar.configuration.CommandOperation.MATERIA_SALVAR;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/materia")
public class MateriaRest {

	private final Map<CommandOperation, ICommand> commands;

	private MateriaServices services = new MateriaServices();

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EntidadeNegocio> create(@RequestBody MateriaDTO materia) {

		ICommand command = commands.get(MATERIA_SALVAR);

		EntidadeNegocio response = command.execute(materia).orElseGet(() -> emptyMessage());

		return ResponseEntity.ok(response);
	}

	private EntidadeNegocio emptyMessage() {
		return null;
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
