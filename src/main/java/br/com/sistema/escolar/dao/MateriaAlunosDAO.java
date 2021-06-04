package br.com.sistema.escolar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.com.sistema.escolar.entity.EntidadeDominio;
import br.com.sistema.escolar.entity.Materia;

public class MateriaAlunosDAO implements IDAO {

	private static final String INSERT = "INSERT INTO aluno_materias(amt_alu_id, amt_mat_id) VALUES(?,?)";

	private static final String FIND_BY_ID = "SELECT * FROM aluno_materias WHERE amt_mat_id = ?";

	private static final String DELETE = "DELETE FROM aluno_materias WHERE amt_mat_id=?";
	
	private Connection connection;

	private PreparedStatement preparedStatement = null;

	public MateriaAlunosDAO(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		Materia materia = (Materia) entidade;

		try {
			preparedStatement = connection.prepareStatement(INSERT);// , Statement.RETURN_GENERATED_KEYS

			for (Integer id : materia.getAlunosId()) {
				preparedStatement.setInt(1, materia.getId());
				preparedStatement.setInt(2, id);
				preparedStatement.executeUpdate();
			}
			preparedStatement.close();

		} catch (SQLException e) {
			throw e;
		} 
	}

	@Override
	public List<EntidadeDominio> listar() {
		
		return null;
	}

	@Override
	public void alterar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub

	}

	@Override
	public void excluir(Integer id) throws SQLException {
		
		try{
			preparedStatement = connection.prepareStatement(DELETE);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			
			preparedStatement.close();
			
		}catch(SQLException e) {
			throw e;
		}

	}

	@Override
	public EntidadeDominio consultar(Integer id) {
		Materia materia = new Materia();
		
		try {			
			preparedStatement = connection.prepareStatement(FIND_BY_ID);

			preparedStatement.setInt(1, id);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				materia.addAlunosId(rs.getInt(1));
			}

			preparedStatement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return materia;
	}

}
