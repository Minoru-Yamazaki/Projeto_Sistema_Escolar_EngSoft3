package br.com.sistema.escolar.strategy;

import br.com.sistema.escolar.entity.EntidadeDominio;

public interface IStrategy {
	public String processar(EntidadeDominio entidade);
}
