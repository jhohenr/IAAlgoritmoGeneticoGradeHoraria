package model;

public class Indisponibilidade {

	public int idProfessor;
	public int horario;
	public int turno;

	public Indisponibilidade() {
		
	}

	public Indisponibilidade(int idProfessor, int horario, int turno) {
		
		this.idProfessor = idProfessor;
		this.horario = horario; 
		this.turno = turno;
	}

}
