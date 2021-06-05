package br.com.sistema.escolar.usecase.crud.materia;

import br.com.sistema.escolar.entity.Materia;
import br.com.sistema.escolar.services.MateriaServices;
import br.com.sistema.escolar.usecase.IFacade;
import br.com.sistema.escolar.web.domain.EntidadeNegocio;
import br.com.sistema.escolar.web.domain.dto.MateriaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MateriaFacade implements IFacade {

    private final MateriaServices materiaServices;

    @Override
    public Optional<EntidadeNegocio> cadastrar(EntidadeNegocio entidade) {
        Materia materia = convertToMateria((MateriaDTO)entidade);

        this.materiaServices.salvar(materia);

        return Optional.of(entidade);
    }

    private Materia convertToMateria(MateriaDTO entidade) {
        Materia materia = new Materia();
        materia.setPeriodo(entidade.getPeriodo());
        materia.setProfessor(entidade.getProfessor());
        materia.setCargaHoraria(entidade.getCargaHoraria());
        materia.setSigla(entidade.getSigla());
        materia.setAlunos(entidade.getAlunos());
        materia.setNome(entidade.getNome());
        //could make a manual convertion or use framework called MapStruct!
        return materia;
    }

    @Override
    public Optional<EntidadeNegocio> excluir(EntidadeNegocio entidade) {
        return Optional.empty();
    }

    @Override
    public Optional<EntidadeNegocio> alterar(EntidadeNegocio entidade) {
        return Optional.empty();
    }

    @Override
    public List<EntidadeNegocio> consultar(EntidadeNegocio entidade) {
        return null;
    }
}
