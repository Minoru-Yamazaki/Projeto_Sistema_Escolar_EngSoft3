package br.com.sistema.escolar.strategy;

import br.com.sistema.escolar.entity.EntidadeDominio;
import br.com.sistema.escolar.entity.Professor;

public class ProValidadorEmail implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {
		Professor professor = (Professor)entidade;
		
		if(professor.getEmail() == null) {
			return "Campo email vazio";
		}
		return null;
	}

}
