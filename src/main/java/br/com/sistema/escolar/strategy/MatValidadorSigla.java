package br.com.sistema.escolar.strategy;

import br.com.sistema.escolar.entity.EntidadeDominio;
import br.com.sistema.escolar.entity.Materia;

public class MatValidadorSigla implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {
		Materia materia = (Materia)entidade;
		
		if(materia.getSigla() == null) {
			return "Campo sigla vazio";
		}
		return null;
	}

}
