package model;

public class Indisponibilidade {

	public String idProfessor;
	public int horario;
	public int turno;

	public Indisponibilidade() {
		
		super();
	}

	public Indisponibilidade(String idProfessor, int horario, int turno) {
		
		super();
		this.idProfessor = idProfessor;
		this.horario = horario; 
		this.turno = turno;
	}

}
