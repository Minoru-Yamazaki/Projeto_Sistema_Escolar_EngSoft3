package br.com.sistema.escolar.strategy;

import br.com.sistema.escolar.entity.EntidadeDominio;
import br.com.sistema.escolar.entity.Professor;

public class ProValidadorTelefone implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {
		Professor professor = (Professor)entidade;
		
		if(professor.getTelefone() == null) {
			return "Campo telefone vazio";
		}
		return null;
	}
}
