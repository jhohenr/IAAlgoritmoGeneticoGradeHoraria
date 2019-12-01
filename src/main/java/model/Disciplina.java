package model;

import java.util.ArrayList;

public class Disciplina {

	public int id;
	public int idCurso;
	public String nome;

	public Disciplina() {
		
	}

	public Disciplina(int id, int idCurso, String nome) {
		
		this.id = id;
		this.idCurso = idCurso;
		this.nome = nome;
	}
	

	public Aula aula(ArrayList<Aula> aulas) {
		
		for (Aula aula : aulas) {
			if (aula.idDisciplina == id) {
				return aula;
			}
		}
		
		return null;
	}

}
