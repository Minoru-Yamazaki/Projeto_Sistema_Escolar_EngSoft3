package br.com.sistema.escolar.rotes;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Routes {

	@RequestMapping("/home")
	public String index() {
	    return "Home";
	}
	
	@RequestMapping("/aluno")
	public String aluno() {
	    return "Aluno";
	}
	
	@RequestMapping("/materia")
	public String materia() {
	    return "Materia";
	}
	
	@RequestMapping("/professor")
	public String professor() {
	    return "Professor";
	}
}
