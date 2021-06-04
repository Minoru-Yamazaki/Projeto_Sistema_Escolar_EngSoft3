package br.com.sistema.escolar.strategy;

import br.com.sistema.escolar.entity.EntidadeDominio;
import br.com.sistema.escolar.entity.Professor;

public class ProValidadorRegistro implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {
		Professor professor = (Professor)entidade;
		
		if(professor.getRegistro() == null) {
			return "Campo registro vazio";
		}
		return null;
	}

}
