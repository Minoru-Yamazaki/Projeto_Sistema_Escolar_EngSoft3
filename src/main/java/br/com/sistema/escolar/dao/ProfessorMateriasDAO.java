package br.com.sistema.escolar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.com.sistema.escolar.entity.EntidadeDominio;
import br.com.sistema.escolar.entity.Professor;

public class ProfessorMateriasDAO implements IDAO {

	private static final String INSERT = "INSERT INTO professor_materias(pmt_pro_id, pmt_mat_id) VALUES(?,?)";

	private static final String FIND_BY_ID = "SELECT * FROM professor_materias WHERE pmt_pro_id = ?";

	private static final String DELETE = "DELETE FROM professor_materias WHERE pmt_pro_id=?";
	
	private Connection connection;

	private PreparedStatement preparedStatement = null;

	public ProfessorMateriasDAO(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		Professor professor = (Professor) entidade;

		try {
			preparedStatement = connection.prepareStatement(INSERT);// , Statement.RETURN_GENERATED_KEYS

			for (Integer id : professor.getMateriasId()) {
				preparedStatement.setInt(1, professor.getId());
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
		Professor professor = new Professor();
		
		try {			
			preparedStatement = connection.prepareStatement(FIND_BY_ID);

			preparedStatement.setInt(1, id);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				professor.addMateriasId(rs.getInt(1));
			}

			preparedStatement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return professor;
	}

}
