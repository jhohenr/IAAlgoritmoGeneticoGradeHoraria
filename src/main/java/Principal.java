import util.Dados;

import java.util.ArrayList;

import model.Aula;
import model.Curso;
import model.Disciplina;
import model.Indisponibilidade;
import model.Professor;

public class Principal {

	public static void main(String[] args) {

		ArrayList<Curso> cursos = Dados.lerCursos();
		ArrayList<Disciplina> disciplinas = Dados.lerDisciplinas();
		ArrayList<Professor> professores = Dados.lerProfessores();
		ArrayList<Aula> aulas = Dados.lerAulas(disciplinas);
		ArrayList<Indisponibilidade> indisponibilidades = Dados.lerIndisponibilidades();
		
//		for (Curso curso : cursos) {
//			System.out.println(curso.nome);
//		}
		
//		for (Disciplina disciplina : disciplinas) {
//			System.out.println(disciplina.idCurso);
//		}
		
//		for (Professor professor : professores) {
//			System.out.println(professor.nome);
//		}
		
//		for (Aula aula : aulas) {
//			System.out.println(aula.id);
//		}
		
		for (Indisponibilidade indisponibilidade : indisponibilidades) {
			System.out.println(indisponibilidade.idProfessor);
			System.out.println(indisponibilidade.horario);
			System.out.println(indisponibilidade.turno);
			System.out.println("---");
		}
	}

}
