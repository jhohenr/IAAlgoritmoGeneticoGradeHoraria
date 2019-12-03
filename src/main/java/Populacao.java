import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import model.Curso;
import model.Disciplina;

public class Populacao {

	ArrayList<Individuo> individuos = new ArrayList<Individuo>();
	
	public static Populacao gerar(int tamanho, int tamCromossomo, ArrayList<Disciplina> disciplinas) {
		
		Populacao populacao = new Populacao();
		
		for (int i = 0; i < tamanho; i++) {
			int[] cromossomo = new int[tamCromossomo];
			
			for (int j = 0; j < tamCromossomo; j++) {
				Disciplina disciplina = disciplinas.get(new Random().nextInt(disciplinas.size()));
				cromossomo[j] = disciplina.id;
			}
			
			populacao.individuos.add(new Individuo(cromossomo));
		}
		
		return populacao;
	}
	
	public void calcular(ArrayList<Curso> cursos, ArrayList<Disciplina> disciplinas) {
		
		for (Individuo individuo : individuos) {
			individuo.calcularApticao(cursos, disciplinas);
		}
	}
	

	public ArrayList<Individuo> ordenar() {
		
		Collections.sort(individuos, new Comparator<Individuo>() {
			public int compare(Individuo o1, Individuo o2) {
				
				if (o1.fitness < o2.fitness) {
			      return 1;
			    } else if (o1.fitness > o2.fitness) {
			      return -1;
			    }
				
			    return 0;
			}
		});
		
		return individuos;
	}
	
	public Populacao selecionar(int elitismo, ArrayList<Curso> cursos, ArrayList<Disciplina> disciplinas) {
		
		Populacao novaPopulacao = new Populacao();
		int maxElitismo = individuos.size() * elitismo / 100;

		calcular(cursos, disciplinas);
		ordenar();
		
		for (int i = 0; i < individuos.size(); i++) {
			if (i < maxElitismo) {
				novaPopulacao.individuos.add(individuos.get(i));
			} else {
				novaPopulacao.individuos.add(roletaSimples());
			}
		}
		
		return novaPopulacao;
	}
	
	public Populacao crossover(int tamCromossomo) {
		
		Populacao novaPopulacao = new Populacao();
		
		while (novaPopulacao.individuos.size() < individuos.size()) {
			
			Random rand = new Random();
			int breakpoint = rand.nextInt(tamCromossomo);
			Individuo pai1 = individuos.get(rand.nextInt(individuos.size()));
			Individuo pai2 = individuos.get(rand.nextInt(individuos.size()));
			
		    int[] cromossomo1 = pai1.cromossomo.clone();
		    int[] cromossomo2 = pai2.cromossomo.clone();
		    
		    for (int i = 0; i < breakpoint; i++) {
		    	cromossomo1[i] = pai2.cromossomo[i];
		    }
		    
		    for (int i = breakpoint; i < tamCromossomo; i++) {
		    	cromossomo2[i] = pai1.cromossomo[i];
		    }
			
		    novaPopulacao.individuos.add(new Individuo(cromossomo1));
		    novaPopulacao.individuos.add(new Individuo(cromossomo2));
		}
	    
		return novaPopulacao;
	}
	
	public Populacao mutacao() {
		
		Populacao novaPopulacao = new Populacao();
		

	    
		return novaPopulacao;
	}
	
	private Individuo roletaSimples() {
		
		int total = 0;
		
		for (Individuo individuo : individuos) {
			total += individuo.fitness;
		}
		
		int rand = new Random().nextInt(total + 1);
		int parcial = 0;
		
		for (Individuo individuo : individuos) {
			parcial += individuo.fitness;
			
			if (parcial >= rand) {
				return individuo;
			}
		}
		
		return null;
	}
	
	public void exibir() {
		
		for (Individuo individuo : individuos) {
			individuo.exibir();
			System.out.print("\n");
		}
	}
	
}
