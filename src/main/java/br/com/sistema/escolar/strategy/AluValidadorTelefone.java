package br.com.sistema.escolar.strategy;

import br.com.sistema.escolar.entity.Aluno;
import br.com.sistema.escolar.entity.EntidadeDominio;

public class AluValidadorTelefone implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {
		Aluno aluno = (Aluno)entidade;
		
		if(aluno.getTelefone() == null) {
			return "Campo telefone vazio";
		}
		return null;
	}

}
