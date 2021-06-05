package br.com.sistema.escolar.web.domain.dto;

import br.com.sistema.escolar.entity.Aluno;
import br.com.sistema.escolar.entity.Professor;
import br.com.sistema.escolar.web.domain.EntidadeNegocio;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class MateriaDTO extends EntidadeNegocio {
    private String sigla;
    private String nome;
    private Integer cargaHoraria;
    private Professor professor;
    private Integer professorId;
    private String periodo;
    private List<String> erros;
    private List<Aluno> alunos;
    private List<Integer> alunosId;
}
