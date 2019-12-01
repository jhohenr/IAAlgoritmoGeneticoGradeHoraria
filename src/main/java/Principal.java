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
	static int roletaSimples = 100 - elitismo; // 90%
	
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

		ArrayList<Individuo> populacao = gerarPopulacaoInicial();
		calcularPopulacao(populacao);
		ordenarPopulacao(populacao);
		exibirPopulacao(populacao);
		
		roletaSimples(populacao);
		ArrayList<Individuo> novaPopulacao = gerarPopulacao(populacao);
		System.out.println(novaPopulacao.size());
	}
	
	public static ArrayList<Individuo> gerarPopulacao(ArrayList<Individuo> populacao) {

		ArrayList<Individuo> novaPopulacao = new ArrayList<Individuo>();
		int maxElitismo = tamPopulacao * elitismo / 100;

		calcularPopulacao(populacao);
		ordenarPopulacao(populacao);
		
		for (int i = 0; i < tamPopulacao; i++) {
			if (i < maxElitismo) {
				novaPopulacao.add(populacao.get(i));
			} else {
				Individuo x;
				Individuo y;
				Individuo filho;
			}
		}
		
		return novaPopulacao;
	}

	public static ArrayList<Individuo> gerarPopulacaoInicial() {

		ArrayList<Individuo> populacao = new ArrayList<Individuo>();
		
		for (int i = 0; i < tamPopulacao; i++) {
			int[] cromossomo = new int[tamCromossomo];
			
			for (int j = 0; j < tamCromossomo; j++) {
				cromossomo[j] = sortearDisciplina();
			}
			
			populacao.add(new Individuo(cromossomo));
		}
		
		return populacao;
	}
	
	public static int sortearDisciplina() {
		
	    Random rand = new Random();
	    Disciplina item = disciplinas.get(rand.nextInt(disciplinas.size()));
	    
	    return item.id;
	}
	
	public static void exibirPopulacao(ArrayList<Individuo> populacao) {
		
		for (Individuo individuo : populacao) {
			individuo.exibir();
			System.out.print("\n");
		}
	}
	
	public static void calcularPopulacao(ArrayList<Individuo> populacao) {
		
		for (Individuo individuo : populacao) {
			individuo.calcularApticao(cursos, disciplinas);
		}
	}
	
	public static ArrayList<Individuo> ordenarPopulacao(ArrayList<Individuo> populacao) {
		
		Collections.sort(populacao, new Comparator<Individuo>() {
			public int compare(Individuo o1, Individuo o2) {
				
				if (o1.fitness < o2.fitness) {
			      return 1;
			    } else if (o1.fitness > o2.fitness) {
			      return -1;
			    }
				
			    return 0;
			}
		});
		
		return populacao;
	}
	
	public static Individuo roletaSimples(ArrayList<Individuo> populacao) {
		
		int total = 0;
		
		for (Individuo individuo : populacao) {
			total += individuo.fitness;
		}
		
		for (Individuo individuo : populacao) {
			int probabilidade = individuo.fitness * 100 / total;
		}
		
		return populacao.get(0);
	}

}
