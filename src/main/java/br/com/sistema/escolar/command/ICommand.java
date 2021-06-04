package br.com.sistema.escolar.command;

import br.com.sistema.escolar.entity.EntidadeDominio;

public interface ICommand {

	public Object execute(EntidadeDominio entidade);
}
