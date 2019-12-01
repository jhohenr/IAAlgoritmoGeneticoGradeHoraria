package model;

import java.util.ArrayList;

public class Curso {

	public int id;
	public String nome;

	public Curso() {
		
	}

	public Curso(int id, String nome) {
		
		this.id = id;
		this.nome = nome;
	}
	
	public ArrayList<Disciplina> disciplinas(ArrayList<Disciplina> disciplinas) {
		ArrayList<Disciplina> di = new ArrayList<Disciplina>();
		
		for (Disciplina disciplina : disciplinas) {
			if (disciplina.idCurso == id) {
				di.add(disciplina);
			}
		}
		
		return di;
	}

}
