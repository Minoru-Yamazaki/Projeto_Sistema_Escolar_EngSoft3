package br.com.sistema.escolar.services;

import java.util.List;

import br.com.sistema.escolar.entity.EntidadeDominio;

public interface IServices {
	
	public EntidadeDominio salvar(EntidadeDominio entidade);
	
	public void alterar(EntidadeDominio entidade);
	
	public void excluir(Integer id);

	public EntidadeDominio consultar(Integer id);
	
	public List<EntidadeDominio> listar();
}
