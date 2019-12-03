import util.Dados;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import model.Aula;
import model.Curso;
import model.Disciplina;
import model.Indisponibilidade;
import model.Professor;

public class Principal {

	static int elitismo = 10; // 10%
	
	static int tamPopulacao = 100;
	static int tamCromossomo = 240; // 12 cursos x 20
	
	static ArrayList<Curso> cursos;
	static ArrayList<Disciplina> disciplinas;
	static ArrayList<Professor> professores;
	static ArrayList<Aula> aulas;
	static ArrayList<Indisponibilidade> indisponibilidades;
	
	public static void main(String[] args) {

		cursos = Dados.lerCursos();
		disciplinas = Dados.lerDisciplinas();
		professores = Dados.lerProfessores();
		aulas = Dados.lerAulas(disciplinas);
		indisponibilidades = Dados.lerIndisponibilidades();

		Populacao populacao = Populacao.gerar(tamPopulacao, tamCromossomo, disciplinas);
		populacao.calcular(cursos, disciplinas);
		populacao.ordenar();
		
		int i = 0;
		
		while (i < 1) {
			populacao = populacao.selecionar(elitismo, cursos, disciplinas);
			populacao = populacao.crossover(tamCromossomo);
			// populacao = populacao.mutacao();
			populacao.exibir();
			i++;
		}
	}

}
