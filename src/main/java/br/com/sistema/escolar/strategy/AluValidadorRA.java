package br.com.sistema.escolar.strategy;

import br.com.sistema.escolar.entity.Aluno;
import br.com.sistema.escolar.entity.EntidadeDominio;

public class AluValidadorRA implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {
		Aluno aluno = (Aluno)entidade;
		
		if(aluno.getRa() == null) {
			return "Campo RA vazio";
		}
		return null;
	}

}
