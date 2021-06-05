package br.com.sistema.escolar.usecase;

import br.com.sistema.escolar.web.domain.EntidadeNegocio;

import java.util.List;
import java.util.Optional;

public interface IFacade {

    Optional<EntidadeNegocio> cadastrar(EntidadeNegocio entidade);
    Optional<EntidadeNegocio> excluir(EntidadeNegocio entidade);
    Optional<EntidadeNegocio> alterar(EntidadeNegocio entidade);
    List<EntidadeNegocio> consultar(EntidadeNegocio entidade);
}
