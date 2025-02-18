package bookle.rest.dto;

import java.util.LinkedList;

public class DiaAgendaRequestDto {

	private String fecha;
	private LinkedList<String> turnos = new LinkedList<>();

	public DiaAgendaRequestDto() {
		
	}
	
	public String getFecha() {
		return fecha;
	}
	
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public LinkedList<String> getTurnos() {
		return turnos;
	}
	
	public void setTurnos(LinkedList<String> turnos) {
		this.turnos = turnos;
	}
}
