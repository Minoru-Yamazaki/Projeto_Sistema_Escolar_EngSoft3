package br.com.sistema.escolar.command;

import br.com.sistema.escolar.web.domain.EntidadeNegocio;

import java.util.Optional;

public interface ICommand {

	Optional<EntidadeNegocio> execute(EntidadeNegocio entidadeNegocio);
}
