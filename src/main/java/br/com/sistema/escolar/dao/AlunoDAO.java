package br.com.sistema.escolar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.sistema.escolar.entity.Aluno;
import br.com.sistema.escolar.entity.EntidadeDominio;

public class AlunoDAO implements IDAO {

	private static final String UPDATE = "UPDATE alunos SET alu_nome=?, alu_ra=?, alu_idade=?, alu_email=?, alu_telefone=? WHERE alu_id=?";

	private static final String INSERT = "INSERT INTO alunos(alu_nome, alu_ra, alu_idade, alu_email, alu_telefone) VALUES(?,?,?,?,?)";

	private static final String FIND_BY_ID = "SELECT * FROM alunos WHERE alu_id = ?";
	
	private static final String FIND_ALL = "SELECT * FROM alunos";

	private static final String DELETE = "DELETE FROM alunos WHERE alu_id=?";
	
	private Connection connection;
	
	private List<EntidadeDominio> alunos;
	
	private PreparedStatement preparedStatement = null;

	public AlunoDAO(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException{
		Aluno aluno = (Aluno) entidade;

		try {
			preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setString(1, aluno.getNome());
			preparedStatement.setString(2, aluno.getRa());
			preparedStatement.setInt(3, aluno.getIdade());
			preparedStatement.setString(4, aluno.getEmail());
			preparedStatement.setString(5, aluno.getTelefone());
			
			preparedStatement.executeUpdate();

			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				aluno.setId(rs.getInt(1));
			}			
			
			preparedStatement.close();
			
		}catch(SQLException e) {			
			throw e;
		}
	}

	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		Aluno aluno = (Aluno) entidade;

		try {
			preparedStatement = connection.prepareStatement(UPDATE);
			
			preparedStatement.setString(1, aluno.getNome());
			preparedStatement.setString(2, aluno.getRa());
			preparedStatement.setInt(3, aluno.getIdade());
			preparedStatement.setString(4, aluno.getEmail());
			preparedStatement.setInt(5, aluno.getId());
			preparedStatement.setString(6, aluno.getTelefone());
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
			
		}catch(SQLException e) {
			throw e;
		}

	}

	@Override
	public EntidadeDominio consultar(Integer id) {
		Aluno aluno = new Aluno();
		
		if(id != null) {
			try {
				preparedStatement = connection.prepareStatement(FIND_BY_ID);
				
				preparedStatement.setInt(1, id);

				ResultSet rs = preparedStatement.executeQuery();

				if (rs.next()) {
					aluno.setId(rs.getInt(1));
					aluno.setNome(rs.getString(2));
					aluno.setRa(rs.getString(3));
					aluno.setIdade(rs.getInt(4));
					aluno.setEmail(rs.getString(5));
					aluno.setTelefone(rs.getString(6));;
				}

				preparedStatement.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return aluno;
	}
	
	@Override
	public List<EntidadeDominio> listar() {
		alunos = new ArrayList<EntidadeDominio>();
		try {
			preparedStatement = connection.prepareStatement(FIND_ALL);
			
			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next()) {
				
				alunos.add(new Aluno(rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getInt(4),
						rs.getString(5),
						rs.getString(6)));
			}

			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alunos;
	}
}
