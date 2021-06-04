package br.com.sistema.escolar.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Professor extends EntidadeDominio{

	private String email;
	private String telefone;
	private String registro;
	private String observacoes;
	private List<String> erros;
	private List<Materia> materias;
	private List<Integer> materiasId;

	public Professor(String nome, String email, String telefone, String registro, String observacoes) {
		super(nome);
		this.email = email;
		this.telefone = telefone;
		this.registro = registro;
		this.observacoes = observacoes;
	}
	
	public Professor(Integer id, String nome, String email, String telefone, String registro, String observacoes) {
		super(id, nome);
		this.email = email;
		this.telefone = telefone;
		this.registro = registro;
		this.observacoes = observacoes;
	}
	
	public void addMaterias(Materia materia) {
		if (materias == null) {
			materias = new ArrayList<Materia>();
		}
		materias.add(materia);
	}
	
	public void addMateriasId(Integer id) {
		if (materiasId == null) {
			materiasId = new ArrayList<Integer>();
		}
		materiasId.add(id);
	}
	
	public void addErros(String erro) {
		if (erros == null) {
			erros = new ArrayList<String>();
		}
		erros.add(erro);
	}

}
