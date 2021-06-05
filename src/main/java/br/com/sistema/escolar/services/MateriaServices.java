package br.com.sistema.escolar.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sistema.escolar.exceptions.BusinessException;
import br.com.sistema.escolar.web.domain.DefaultResponseMessage;
import org.springframework.stereotype.Service;

import br.com.sistema.escolar.dao.AlunoDAO;
import br.com.sistema.escolar.dao.IDAO;
import br.com.sistema.escolar.dao.MateriaAlunosDAO;
import br.com.sistema.escolar.dao.MateriaDAO;
import br.com.sistema.escolar.dao.MateriaProfessoresDAO;
import br.com.sistema.escolar.dao.ProfessorDAO;
import br.com.sistema.escolar.entity.Aluno;
import br.com.sistema.escolar.entity.EntidadeDominio;
import br.com.sistema.escolar.entity.Materia;
import br.com.sistema.escolar.entity.Professor;
import br.com.sistema.escolar.strategy.IStrategy;
import br.com.sistema.escolar.strategy.MatValidadorCargaHoraria;
import br.com.sistema.escolar.strategy.MatValidadorPeriodo;
import br.com.sistema.escolar.strategy.MatValidadorSigla;
import br.com.sistema.escolar.strategy.ValidadorNome;
import br.com.sistema.escolar.util.Conexao;

@Service
public class MateriaServices implements IServices {

	private Connection connection;
	private List<EntidadeDominio> materias;
	private IDAO dao;
	private Materia materia;
	private List<IStrategy> rnsMateria;
	private List<String> erros;
	
	@Override
	public EntidadeDominio salvar(EntidadeDominio entidade) {
		Materia mat = (Materia)entidade;
		List<String> errors = executaRnMateria(entidade);

		if(!errors.isEmpty())
			throw new BusinessException(erros);

		if(errors.isEmpty()) {
			try {
				connection = Conexao.getConnection();
				connection.setAutoCommit(false);

				dao = new MateriaDAO(connection);
				dao.salvar(mat);
				
				if(mat.getProfessorId() != null) {
					dao = new MateriaProfessoresDAO(connection);
					dao.salvar(mat);				
				}
				
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
		return mat;
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

			dao = new MateriaProfessoresDAO(connection);
			dao.excluir(id);
			
			dao = new MateriaAlunosDAO(connection);
			dao.excluir(id);
			
			dao = new MateriaDAO(connection);
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
		Materia materia2;
		try {
			connection = Conexao.getConnection();

			dao = new MateriaDAO(connection);
			materia = (Materia) dao.consultar(id);

			dao = new MateriaProfessoresDAO(connection);
			materia2 = (Materia)dao.consultar(id);
			materia.setPeriodo(materia2.getPeriodo());
			
			dao = new ProfessorDAO(connection);
			materia.setProfessor((Professor)dao.consultar(materia2.getProfessorId()));
			
			dao = new MateriaAlunosDAO(connection);
			materia2 = (Materia)dao.consultar(id);
			
			dao = new AlunoDAO(connection);
			for(Integer i : materia2.getAlunosId()) {
				materia.addAlunos((Aluno)dao.consultar(i));
			}
			
			connection.close();

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return materia;
	}

	@Override
	public List<EntidadeDominio> listar() {
		materias = new ArrayList<>();
		try {
			connection = Conexao.getConnection();

			dao = new MateriaDAO(connection);
			materias = dao.listar();

			dao = new ProfessorDAO(connection);

			for (EntidadeDominio e : materias) {
				Materia materia = (Materia) e;
				materia.setProfessor((Professor) dao.consultar(materia.getProfessorId()));
			}

			connection.close();

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return materias;
	}
	
	
	private void rnMateria(EntidadeDominio entidade) {
		Materia materia = (Materia)entidade;
		rnsMateria = new ArrayList<IStrategy>();
		
		if(materia.getProfessorId() != null) {
			rnsMateria.add(new MatValidadorPeriodo());
		}
		
		rnsMateria.add(new MatValidadorSigla());
		rnsMateria.add(new MatValidadorCargaHoraria());
		rnsMateria.add(new ValidadorNome());				
	}
	
	private List<String> executaRnMateria(EntidadeDominio entidade){
		erros = new ArrayList<String>();
		rnMateria(entidade);
		
		for(IStrategy rn : rnsMateria) {
			String erro = rn.processar(entidade);
			if(erro != null) {
				erros.add(erro);
			}			
		}
		return erros;
	}

}
