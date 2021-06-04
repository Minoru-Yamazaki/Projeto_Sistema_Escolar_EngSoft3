package br.com.sistema.escolar.strategy;

import br.com.sistema.escolar.entity.Aluno;
import br.com.sistema.escolar.entity.EntidadeDominio;

public class AluValidadorEmail implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {
		Aluno aluno = (Aluno)entidade;
		
		if(aluno.getEmail() == null) {
			return "Campo email vazio";
		}
		return null;
	}

}
