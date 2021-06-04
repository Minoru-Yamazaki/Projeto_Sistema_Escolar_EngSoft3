package br.com.sistema.escolar.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.sistema.escolar.dao.IDAO;
import br.com.sistema.escolar.dao.MateriaDAO;
import br.com.sistema.escolar.dao.ProfessorDAO;
import br.com.sistema.escolar.dao.ProfessorMateriasDAO;
import br.com.sistema.escolar.entity.EntidadeDominio;
import br.com.sistema.escolar.entity.Materia;
import br.com.sistema.escolar.entity.Professor;
import br.com.sistema.escolar.strategy.IStrategy;
import br.com.sistema.escolar.strategy.ProValidadorEmail;
import br.com.sistema.escolar.strategy.ProValidadorRegistro;
import br.com.sistema.escolar.strategy.ProValidadorTelefone;
import br.com.sistema.escolar.strategy.ValidadorNome;
import br.com.sistema.escolar.util.Conexao;

@Service
public class ProfessorServices implements IServices {

	private Professor professor;
	private Materia materia;
	private IDAO dao;
	private Connection connection;
	private List<EntidadeDominio> professores;
	private List<IStrategy> rnsProfessor;
	private List<String> erros;

	@Override
	public EntidadeDominio salvar(EntidadeDominio entidade) {
		Professor prof = (Professor) entidade;
		prof.setErros(executaRnProfessor(entidade));
		
		if(prof.getErros().isEmpty()) {
			try {
				connection = Conexao.getConnection();
				connection.setAutoCommit(false);
				
				dao = new ProfessorDAO(connection);
				dao.salvar(prof);

				connection.commit();
				
			} catch (SQLException | ClassNotFoundException e) {
				try {
					connection.rollback();
				}catch(SQLException e2) {
					e2.printStackTrace();
				}
				e.printStackTrace();
			}finally {
				try {
					connection.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return prof;
	}

	@Override
	public void alterar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub

	}

	@Override
	public void excluir(Integer id) {
		try {
			connection = Conexao.getConnection();
			connection.setAutoCommit(false);

			dao = new ProfessorMateriasDAO(connection);
			dao.excluir(id);
			
			dao = new ProfessorDAO(connection);
			dao.excluir(id);
						
			connection.commit();

		} catch (SQLException | ClassNotFoundException e) {
			try {
				connection.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public EntidadeDominio consultar(Integer id) {
		try {
			connection = Conexao.getConnection();

			dao = new ProfessorDAO(connection);
			professor = (Professor) dao.consultar(id);

			dao = new ProfessorMateriasDAO(connection);
			Professor prof = (Professor)dao.consultar(id);
						
			if(prof.getMateriasId() != null) {
				dao = new MateriaDAO(connection);
				for (Integer i : prof.getMateriasId()) {
					professor.addMaterias((Materia) dao.consultar(i));
				}
			}

			connection.close();

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return professor;
	}

	@Override
	public List<EntidadeDominio> listar() {
		try {
			connection = Conexao.getConnection();
			
			dao = new ProfessorDAO(connection);
			professores = dao.listar();
			
			connection.close();
			
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return professores;
	}
	
	private void rnProfessor() {
		rnsProfessor = new ArrayList<IStrategy>();
		
		rnsProfessor.add(new ProValidadorEmail());
		rnsProfessor.add(new ProValidadorTelefone());		
		rnsProfessor.add(new ProValidadorRegistro());
		rnsProfessor.add(new ValidadorNome());				
	}
			
	private List<String> executaRnProfessor(EntidadeDominio entidade){
		erros = new ArrayList<String>();
		rnProfessor();
		
		for(IStrategy rn : rnsProfessor) {
			String erro = rn.processar(entidade);
			if(erro != null) {
				erros.add(erro);
			}			
		}
		return erros;
	}

}
