package br.com.sistema.escolar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.sistema.escolar.entity.EntidadeDominio;
import br.com.sistema.escolar.entity.Materia;
import br.com.sistema.escolar.test.Periodo;

public class MateriaDAO implements IDAO {

	private static final String UPDATE = "UPDATE materias SET mat_sigla=?, mat_nome=?, mat_carga_horaria=? WHERE mat_id=?";
	private static final String FIND_BY_ID = "SELECT * FROM materias WHERE mat_id = ?";
	private static final String INSERT = "INSERT INTO materias(mat_nome, mat_sigla, mat_carga_horaria) VALUES(?,?,?)";
	private static final String DELETE = "DELETE FROM materias WHERE mat_id=?";
	private static final String FIND_ALL = "SELECT * FROM materias";
	
	private List<EntidadeDominio> materias;
	private Materia materia;
	
	private Connection connection;
	private PreparedStatement preparedStatement = null;

	public MateriaDAO(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		Materia materia = (Materia) entidade;

		try {
			preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, materia.getNome());
			preparedStatement.setString(2, materia.getSigla());
			preparedStatement.setInt(3, materia.getCargaHoraria());
			
			preparedStatement.executeUpdate();

			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				materia.setId(rs.getInt(1));
			}
			preparedStatement.close();

		} catch (SQLException e) {
			throw e;
		}
	}

	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		Materia materia = (Materia) entidade;

		try {
			preparedStatement = connection.prepareStatement(UPDATE);

			preparedStatement.setString(1, materia.getSigla());
			preparedStatement.setString(2, materia.getNome());
			preparedStatement.setInt(3, materia.getCargaHoraria());

			preparedStatement.executeUpdate();

			preparedStatement.close();

		} catch (SQLException e) {
			throw e;
		}
	}

	@Override
	public void excluir(Integer id) throws SQLException {
		try {
			preparedStatement = connection.prepareStatement(DELETE);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();

			preparedStatement.close();

		} catch (SQLException e) {
			throw e;
		}
	}
	
	
	@Override
	public EntidadeDominio consultar(Integer id) {
		
		try {
			preparedStatement = connection.prepareStatement(FIND_BY_ID);

			preparedStatement.setInt(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				materia = new Materia();
			
				materia.setId(resultSet.getInt(1));
				materia.setSigla(resultSet.getString(2));
				materia.setNome(resultSet.getString(3));
				materia.setCargaHoraria(resultSet.getInt(4));
			}
			
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return materia;
	}

	@Override
	public List<EntidadeDominio> listar() {
		materias = new ArrayList<EntidadeDominio>();

		try {
			preparedStatement = connection.prepareStatement(FIND_ALL);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				materias.add(new Materia(
						resultSet.getInt(1),
						resultSet.getString(3),
						resultSet.getString(2),
						resultSet.getInt(4)));
			}

			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return materias;
	}

	private Periodo newPeriodo(String periodo) {
		if (periodo.equals("Manhã")) {
			return Periodo.MANHA;
		}
		if (periodo.equals("Tarde")) {
			return Periodo.TARDE;
		}
		return Periodo.NOTE;
	}

}
