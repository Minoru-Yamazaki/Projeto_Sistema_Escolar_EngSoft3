package br.com.sistema.escolar.strategy;

import br.com.sistema.escolar.entity.EntidadeDominio;

public class ValidadorNome implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {
		
		if(entidade.getNome() == null) {
			return "Campo nome vazio";
		}
		return null;
	}

}
