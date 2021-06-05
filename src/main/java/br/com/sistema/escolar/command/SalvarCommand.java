package br.com.sistema.escolar.command;

import br.com.sistema.escolar.usecase.IFacade;
import br.com.sistema.escolar.web.domain.EntidadeNegocio;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class SalvarCommand implements ICommand{

	private final IFacade facade;

	@Override
	public Optional<EntidadeNegocio> execute(EntidadeNegocio entidadeNegocio) {
		return this.facade.cadastrar(entidadeNegocio);
	}
}
