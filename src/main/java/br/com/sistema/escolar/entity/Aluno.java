package br.com.sistema.escolar.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Aluno extends EntidadeDominio{

	private String ra;
	private Integer idade;
	private Integer idMateria;
	private String email;
	private String telefone;
	private List<String> erros;
	private List<Materia> materias;
	private List<Integer> materiasId;
	
	public Aluno(String nome, String ra, int idade, String email, String telefone) {
		super(nome);
		this.ra = ra;
		this.idade = idade;
		this.email = email;
		this.telefone = telefone;
	}
	
	public Aluno(Integer id, String nome, String ra, int idade, String email, String telefone) {
		super(id, nome);
		this.ra = ra;
		this.idade = idade;
		this.email = email;
		this.telefone = telefone;
	}
	
	public void addMaterias(Materia materia) {
		if(materias == null) {
			materias = new ArrayList<Materia>();
		}
		materias.add(materia);
	}
	
	public void addMateriasId(Integer id) {
		if(materiasId == null) {
			materiasId = new ArrayList<Integer>();
		}
		materiasId.add(id);
	}
	
	public void addErros(String erro) {
		erros.add(erro);
	}
	
	/*
	 * public void setMateriasId(List<Integer> matIds) { materiasId = matIds; }
	 */
}
