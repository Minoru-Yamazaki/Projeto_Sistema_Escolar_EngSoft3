package br.com.sistema.escolar.factory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import br.com.sistema.escolar.dao.AlunoDAO;
import br.com.sistema.escolar.dao.AlunoMateriasDAO;
import br.com.sistema.escolar.dao.IDAO;
import br.com.sistema.escolar.dao.MateriaDAO;
import br.com.sistema.escolar.util.Conexao;

public class FactoryMethod {

	Map<Class<? extends IDAO>, IDAO> factoryDAO = new HashMap<>();
	private Connection connection = null;
	
	public FactoryMethod() {
		getConnectionMySQL();
		factoryDAO.put(AlunoDAO.class, new AlunoDAO(connection));
		factoryDAO.put(MateriaDAO.class, new MateriaDAO(connection));
		factoryDAO.put(AlunoMateriasDAO.class, new AlunoMateriasDAO(connection));
	}
	
	public IDAO getDAO(Class<? extends IDAO> nomeClasse) {
		return factoryDAO.get(nomeClasse);
	}
	
	public void getConnectionMySQL() {
		
		try {
			connection = Conexao.getConnection();			
		}catch( ClassNotFoundException | SQLException e) {			
			e.printStackTrace();
		}		
	}
}
