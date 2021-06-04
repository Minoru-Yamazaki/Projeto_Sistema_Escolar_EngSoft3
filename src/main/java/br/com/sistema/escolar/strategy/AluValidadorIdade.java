package br.com.sistema.escolar.strategy;

import br.com.sistema.escolar.entity.Aluno;
import br.com.sistema.escolar.entity.EntidadeDominio;

public class AluValidadorIdade implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {
		Aluno aluno = (Aluno) entidade;
		
		if(aluno.getIdade() == null) {
			return "Campo idade vazio";
		}
		if(aluno.getIdade() < 0) {
			return "Preenchimento incorreto do campo idade";
		}
		return null;
	}

}
