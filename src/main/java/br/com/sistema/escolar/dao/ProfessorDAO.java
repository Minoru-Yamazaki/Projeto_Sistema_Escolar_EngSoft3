package br.com.sistema.escolar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.sistema.escolar.entity.EntidadeDominio;
import br.com.sistema.escolar.entity.Professor;

public class ProfessorDAO implements IDAO {

	private static final String UPDATE = "UPDATE professores SET pro_nome=?, pro_email=?, pro_telefone=?, pro_registro=?, pro_observacoes=? WHERE pro_id=?";
	private static final String INSERT = "INSERT INTO professores(pro_nome, pro_email, pro_telefone, pro_registro, pro_observacoes) VALUES(?,?,?,?,?)";
	private static final String FIND_BY_ID = "SELECT * FROM professores WHERE pro_id = ?";
	private static final String FIND_ALL = "SELECT * FROM professores";
	private static final String DELETE = "DELETE FROM professores WHERE pro_id=?";
	
	private final Connection connection;
	private PreparedStatement preparedStatement = null;
	
	private List<EntidadeDominio> professores;

	public ProfessorDAO(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		Professor professor = (Professor) entidade;

		try {
			preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, professor.getNome());
			preparedStatement.setString(2, professor.getEmail());
			preparedStatement.setString(3, professor.getTelefone());
			preparedStatement.setString(4, professor.getRegistro());
			preparedStatement.setString(5, professor.getObservacoes());

			preparedStatement.executeUpdate();

			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				professor.setId(rs.getInt(1));
			}

			preparedStatement.close();

		} catch (SQLException e) {
			throw e;
		}
	}

	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		Professor professor = (Professor) entidade;

		try {
			preparedStatement = connection.prepareStatement(UPDATE);

			preparedStatement.setString(1, professor.getNome());
			preparedStatement.setString(2, professor.getEmail());
			preparedStatement.setString(3, professor.getTelefone());
			preparedStatement.setString(4, professor.getRegistro());
			preparedStatement.setString(5, professor.getObservacoes());
			preparedStatement.setInt(6, professor.getId());

			preparedStatement.executeUpdate();

			preparedStatement.close();

		} catch (SQLException e) {
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
		Professor professor = new Professor();

		if (id != null) {
			try {
				preparedStatement = connection.prepareStatement(FIND_BY_ID);

				preparedStatement.setInt(1, id);

				ResultSet rs = preparedStatement.executeQuery();

				if (rs.next()) {
					professor.setId(rs.getInt(1));
					professor.setNome(rs.getString(2));
					professor.setEmail(rs.getString(3));
					professor.setTelefone(rs.getString(4));
					professor.setRegistro(rs.getString(5));
					professor.setObservacoes(rs.getString(6));
				}
				preparedStatement.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return professor;
	}
	
	@Override
	public List<EntidadeDominio> listar() {
		professores = new ArrayList<EntidadeDominio>();
		try {
			preparedStatement = connection.prepareStatement(FIND_ALL);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				professores.add(new Professor(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6)
						));
			}
			preparedStatement.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return professores;
	}
}
