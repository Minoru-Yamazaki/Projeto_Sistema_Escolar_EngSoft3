package br.com.sistema.escolar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.com.sistema.escolar.entity.Aluno;
import br.com.sistema.escolar.entity.EntidadeDominio;

public class AlunoMateriasDAO implements IDAO {

	private static final String INSERT = "INSERT INTO aluno_materias(amt_alu_id, amt_mat_id) VALUES(?,?)";
	private static final String FIND_BY_ID = "SELECT * FROM aluno_materias WHERE amt_alu_id = ?";
	private static final String DELETE = "DELETE FROM aluno_materias WHERE amt_alu_id=?";
	private static final String UPDATE = "DELETE FROM aluno_materias WHERE amt_alu_id=? AND amt_mat_id=?";
	
	private Connection connection;

	private PreparedStatement preparedStatement = null;

	public AlunoMateriasDAO(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		Aluno aluno = (Aluno) entidade;

		try {
			preparedStatement = connection.prepareStatement(INSERT);// , Statement.RETURN_GENERATED_KEYS

			for (Integer id : aluno.getMateriasId()) {
				preparedStatement.setInt(1, aluno.getId());
				preparedStatement.setInt(2, id);
				preparedStatement.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				throw e;
			}
		}
	}

	@Override
	public List<EntidadeDominio> listar() {
		
		return null;
	}
	
	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		Aluno aluno = (Aluno)entidade;
		
		try {
			preparedStatement = connection.prepareStatement(UPDATE);
			preparedStatement.setInt(1, aluno.getId());
			preparedStatement.setInt(2, aluno.getIdMateria());
			preparedStatement.executeUpdate();
			
			preparedStatement.close();
			
		}catch(SQLException e) {
			throw e;
		}

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
		Aluno aluno = new Aluno();
		
		try {			
			preparedStatement = connection.prepareStatement(FIND_BY_ID);

			preparedStatement.setInt(1, id);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				aluno.addMateriasId(rs.getInt(2));
			}

			preparedStatement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return aluno;
	}

}
