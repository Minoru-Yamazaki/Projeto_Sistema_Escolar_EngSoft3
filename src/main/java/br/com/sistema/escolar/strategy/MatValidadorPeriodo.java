package br.com.sistema.escolar.strategy;

import br.com.sistema.escolar.entity.EntidadeDominio;
import br.com.sistema.escolar.entity.Materia;

public class MatValidadorPeriodo implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {
		Materia materia = (Materia)entidade;
		
		if(materia.getPeriodo() == null) {
			return "Campo período vazio";
		}
		return null;
	}

}
