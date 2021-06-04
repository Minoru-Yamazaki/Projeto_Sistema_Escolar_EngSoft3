package br.com.sistema.escolar.strategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.sistema.escolar.entity.Aluno;
import br.com.sistema.escolar.entity.EntidadeDominio;

public class ValidatorFactory {

	private Map<Class<? extends EntidadeDominio>, List<IStrategy>> rns = new HashMap<Class<? extends EntidadeDominio>, List<IStrategy>>();
	
	public ValidatorFactory() {
		rnAluno();
		rnMateria();
	}
	
	public List<IStrategy> getListaRN(Class<? extends EntidadeDominio> nomeClasse){
		return rns.get(nomeClasse);
	}
	
	private void rnAluno() {
		rns = new HashMap<Class<? extends EntidadeDominio>, List<IStrategy>>();

		AluValidadorRA validadorRA = new AluValidadorRA();
		AluValidadorIdade validadorIdade = new AluValidadorIdade();
		AluValidadorEmail validadorEmail = new AluValidadorEmail();
		AluValidadorTelefone validadorTelefone = new AluValidadorTelefone();
		ValidadorNome validadorNome = new ValidadorNome();
		
		List<IStrategy> rnAluno = new ArrayList<IStrategy>();
		rnAluno.add(validadorRA);
		rnAluno.add(validadorIdade);		
		rnAluno.add(validadorEmail);
		rnAluno.add(validadorTelefone);
		rnAluno.add(validadorTelefone);
		rnAluno.add(validadorNome);
		rns.put(Aluno.class, rnAluno);		
	}
	
	private void rnMateria() {
		
		ValidadorNome validadorNome = new ValidadorNome();
		MatValidadorSigla validadorSigla = new MatValidadorSigla();
		MatValidadorCargaHoraria validadorCargaHoraria = new MatValidadorCargaHoraria();
		
		List<IStrategy> rnAluno = new ArrayList<IStrategy>();
		rnAluno.add(validadorSigla);
		rnAluno.add(validadorCargaHoraria);
		rnAluno.add(validadorNome);
		rns.put(Aluno.class, rnAluno);		
	}
}
