package bookle.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;

public class DiaAgenda implements Serializable{

	private LocalDate fecha;
	private LinkedList<Turno> turno = new LinkedList<>();
	
	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LinkedList<Turno> getTurno() {
		return turno;
	}

	public void setTurno(LinkedList<Turno> turno) {
		this.turno = turno;
	}

	@Override
	public String toString() {
		return "Agenda [fecha=" + fecha + ", turnos=" + turno + "]";
	}

}
