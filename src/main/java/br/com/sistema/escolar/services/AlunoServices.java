package br.com.sistema.escolar.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sistema.escolar.dao.AlunoDAO;
import br.com.sistema.escolar.dao.AlunoMateriasDAO;
import br.com.sistema.escolar.dao.IDAO;
import br.com.sistema.escolar.dao.MateriaDAO;
import br.com.sistema.escolar.entity.Aluno;
import br.com.sistema.escolar.entity.EntidadeDominio;
import br.com.sistema.escolar.entity.Materia;
import br.com.sistema.escolar.strategy.AluValidadorEmail;
import br.com.sistema.escolar.strategy.AluValidadorIdade;
import br.com.sistema.escolar.strategy.AluValidadorRA;
import br.com.sistema.escolar.strategy.AluValidadorTelefone;
import br.com.sistema.escolar.strategy.IStrategy;
import br.com.sistema.escolar.strategy.ValidadorNome;
import br.com.sistema.escolar.util.Conexao;


public class AlunoServices implements IServices {

	private Aluno aluno;
	private IDAO dao;
	private Connection connection;
	private List<EntidadeDominio> alunos;
	private List<IStrategy> rnsAluno;
	private List<String> erros;
	
	/*
	 * public AlunoServices(FactoryMethod factory) { alunoDAO =
	 * factory.getDAO(AlunoDAO.class); materiaDAO =
	 * factory.getDAO(MateriaDAO.class); alunoMateriaDAO =
	 * factory.getDAO(AlunoMateriasDAO.class); }
	 */
	
	@Override
	public EntidadeDominio salvar(EntidadeDominio entidade){
		Aluno aluno = (Aluno) entidade;
		aluno.setErros(executaRnAluno(entidade));
		
		if(aluno.getErros().isEmpty()) {
			try {
				connection = Conexao.getConnection();
				connection.setAutoCommit(false);
				
				dao = new AlunoDAO(connection);
				dao.salvar(aluno);
				
				if(aluno.getMateriasId() != null) {
					dao = new AlunoMateriasDAO(connection);
					dao.salvar(aluno);
				}				
				
				connection.commit();
				
			}catch(ClassNotFoundException | SQLException e) {
				try {
					connection.rollback();
				}catch(SQLException e2) {
					e2.printStackTrace();
				}
				e.printStackTrace();
			}finally {
				try {
					connection.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		return aluno;
	}

	@Override
	public void alterar(EntidadeDominio entidade) {
		
		try {
			connection = Conexao.getConnection();
			connection.setAutoCommit(false);
			
			dao = new AlunoMateriasDAO(connection);
			dao.alterar(entidade);
			
			connection.commit();
			
		}catch(SQLException | ClassNotFoundException e) {
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

	@Override
	public void excluir(Integer id) {
		try {
			connection = Conexao.getConnection();
			connection.setAutoCommit(false);
			
			dao = new AlunoMateriasDAO(connection);
			dao.excluir(id);
			
			dao = new AlunoDAO(connection);
			dao.excluir(id);		
			
			connection.commit();
			
		}catch(SQLException | ClassNotFoundException e) {
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

	@Override
	public Aluno consultar(Integer id) {
		try {
			connection = Conexao.getConnection();
						
			dao = new AlunoDAO(connection);
			aluno = (Aluno) dao.consultar(id);
			
			dao = new AlunoMateriasDAO(connection);
			Aluno alu = (Aluno)dao.consultar(id);
			
			if(alu.getMateriasId() != null) {
				dao = new MateriaDAO(connection);
				
				for(Integer matId : alu.getMateriasId()) {
					aluno.addMaterias((Materia)dao.consultar(matId));
				}
			}
						
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			try{
				connection.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return aluno;
	}
	

	@Override
	public List<EntidadeDominio> listar() {
		try {
			connection = Conexao.getConnection();
			
			dao = new AlunoDAO(connection);
			alunos = dao.listar();
			
			
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return alunos;
	}
	
	
	private void rnAluno() {
		rnsAluno = new ArrayList<IStrategy>();
		
		rnsAluno.add(new AluValidadorRA());
		rnsAluno.add(new AluValidadorIdade());		
		rnsAluno.add(new AluValidadorEmail());
		rnsAluno.add(new AluValidadorTelefone());
		rnsAluno.add(new ValidadorNome());				
	}
	
		
	private List<String> executaRnAluno(EntidadeDominio entidade){
		erros = new ArrayList<String>();
		rnAluno();
		
		for(IStrategy rn : rnsAluno) {
			String erro = rn.processar(entidade);
			if(erro != null) {
				erros.add(erro);
			}			
		}
		return erros;
	}
}
