import java.util.ArrayList;

import model.Curso;
import model.Disciplina;

public class Individuo {

	int[] cromossomo;
	int fitness = -1;
	
	public Individuo(int tamCromossomo) {
		
		cromossomo = new int[tamCromossomo];
	}
	
	public Individuo(int[] cromossomo) {
		
		this.cromossomo = cromossomo;
	}
	
	public void calcularApticao(ArrayList<Curso> cursos, ArrayList<Disciplina> disciplinas) {
		
		// - Turma sem alguma disciplina -30 (verificar a cada dez posi��es do vetor das turma correspondente, se todos os c�digos das aulas aparecem ali)
		// - Choque Hor�rio Professor � 10 (comparar o professor que est� dando aula de dez em dez posi��es do vetor, considerando os diferentes turnos do curso)
		// - Professor indispon�vel -5 (comparar o c�digo do professor em cada aula, com a tabela indisponibilidade)

		fitness = 0;
		
		int contHorario = 0;
		int contCurso = 0;
		int qtdHorarios = 20;
		
		int[] vDisciplinas = new int[qtdHorarios];
		
		for (int i = 0; i < cromossomo.length; i++) {
			if (contHorario == qtdHorarios - 1) { // Verificar a cada x disciplinas
				contHorario = 0;
				
				// Verificar turma sem alguma disciplina
				int contDisciplina = 0;
				
				for (int l = 0; l < vDisciplinas.length; l++) {
					ArrayList<Disciplina> di = cursos.get(contCurso).disciplinas(disciplinas);
					
					for (Disciplina disciplina : di) {
						if (vDisciplinas[l] == disciplina.id) {
							contDisciplina++;
						}
					}
				}

				if (contDisciplina < qtdHorarios) {
					fitness -= 30;
				}
				
				vDisciplinas = new int[qtdHorarios];
				contCurso++;
			}
			
			vDisciplinas[contHorario] = cromossomo[i];
			contHorario++;
		}
	}
	
	public void exibir() {
		
		System.out.print(fitness + ": ");
		
		for (int j = 0; j < cromossomo.length; j++) {
			if (j > 0) {
				System.out.print(",");
			}
			
			System.out.print(cromossomo[j]);
		}
	}
	
}
