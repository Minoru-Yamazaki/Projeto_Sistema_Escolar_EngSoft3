package br.com.sistema.escolar.dao;

import java.sql.SQLException;
import java.util.List;

import br.com.sistema.escolar.entity.EntidadeDominio;

public interface IDAO {

	public void salvar(EntidadeDominio entidade) throws SQLException;
	
	public void alterar(EntidadeDominio entidade) throws SQLException;
	
	public void excluir(Integer id) throws SQLException;

	public EntidadeDominio consultar(Integer id);
	
	public List<EntidadeDominio> listar();
}
