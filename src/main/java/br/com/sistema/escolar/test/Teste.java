package br.com.sistema.escolar.test;

import java.sql.SQLException;

import br.com.sistema.escolar.services.MateriaServices;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Teste {

	public static void main(String[] args) throws SQLException {
		
		/*
		 * Materia engSoftII = new Materia("Engenharia de Software III", "ENS003", 80,
		 * Periodo.NOTE); MateriaDAO materiaDAO = new MateriaDAO();
		 * materiaDAO.salvar(engSoftII);
		 */

		/*
		 * AlunoDAO alunoDAO = new AlunoDAO(); Aluno minoru = new Aluno("Minoru",
		 * "268651", 27, "minoru.rhcp@gmail.com"); alunoDAO.salvar(minoru);
		 */

		/*
		 * AlunoMateriasDAO aluMatDAO = new AlunoMateriasDAO(); aluMatDAO.salvar(1, 1);
		 * aluMatDAO.salvar(1, 3);
		 */

		/*
		 * ProfessorServices profServ = new ProfessorServices(); Professor professor =
		 * profServ.consultar(1); // MateriaDAO materiaDAO = new MateriaDAO(); //
		 * Materia materia = materiaDAO.consultar(4);
		 * 
		 * System.out.println("id professor: " + professor.getId());
		 * System.out.println("nome professor: " + professor.getNome());
		 * System.out.println("telefone professor: " + professor.getTelefone());
		 * System.out.println("registro professor: " + professor.getRegistro());
		 * System.out.println("observações: " + professor.getObservacoes());
		 * System.out.println("-----------------------------------------"); for (Materia
		 * mat : professor.getMaterias()) { System.out.println("id da materia: " +
		 * mat.getId()); System.out.println("sigla da materia: " + mat.getSigla());
		 * System.out.println("nome da materia: " + mat.getNome());
		 * System.out.println("carga horaria: " + mat.getCargaHoraria());
		 * System.out.println("professor id: " + mat.getProfessorId());
		 * System.out.println("periodo: " + mat.getTipoPeriodo());
		 * System.out.println("-----------------------------------------"); }
		 */

		// System.out.println(materia.getNome());

		
		/*
		 * for(Materia mat : aluno.getMaterias()){ System.out.println("Id: " +
		 * mat.getId()); System.out.println("Sigla: " + mat.getSigla());
		 * System.out.println("nome da materia: " + mat.getNome());
		 * System.out.println("carga horaria: " + mat.getCargaHoraria());
		 * System.out.println("professor id: " + mat.getProfessorId());
		 * System.out.println("periodo: " + mat.getTipoPeriodo()); }
		 */
		 log.info("teste");
		
	
		
	}

}
