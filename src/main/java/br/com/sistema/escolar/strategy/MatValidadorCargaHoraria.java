package br.com.sistema.escolar.strategy;

import br.com.sistema.escolar.entity.EntidadeDominio;
import br.com.sistema.escolar.entity.Materia;

public class MatValidadorCargaHoraria implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {
		Materia materia = (Materia)entidade;
		
		if(materia.getCargaHoraria() == null) {
			return "Campo carga horária vazio";
		}
		return null;
	}
	
}
