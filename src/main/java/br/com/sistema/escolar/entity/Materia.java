package br.com.sistema.escolar.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Materia extends EntidadeDominio{

	private String sigla;
	private Integer cargaHoraria;
	private Professor professor;
	private Integer professorId;
	private String periodo;
	private List<String> erros;
	private List<Aluno> alunos;
	private List<Integer> alunosId;
	
	public Materia(String nome, String sigla, int cargaHoraria) {
		super(nome);
		this.sigla = sigla;
		this.cargaHoraria = cargaHoraria;
	}
	
	public Materia(String nome, String sigla, int cargaHoraria, Integer professorId, String periodo) {
		super(nome);
		this.sigla = sigla;
		this.cargaHoraria = cargaHoraria;
		this.professorId = professorId;
		this.periodo = periodo;
	}
	
	public Materia(Integer id, String nome, String sigla, int cargaHoraria) {
		super(id, nome);
		this.sigla = sigla;
		this.cargaHoraria = cargaHoraria;
	}

	public void addAlunos(Aluno aluno) {
		if(alunos == null) {
			alunos = new ArrayList<Aluno>();
		}
		alunos.add(aluno);
	}
	
	public void addAlunosId(Integer id) {
		if(alunosId == null) {
			alunosId = new ArrayList<Integer>();
		}
		alunosId.add(id);
	}
	
	public void addErros(String erro) {
		if(erros == null) {
			erros = new ArrayList<String>();
		}
		erros.add(erro);
	}
	 
}
