package eventos.modelo;

import java.time.LocalDateTime;


public class Ocupacion {
	
	
	private LocalDateTime fechaInicio;
	private LocalDateTime fechaFin;	
	private EspacioFisico espacioFisico;
		
	public Ocupacion(){
		
	}
	
	public Ocupacion(LocalDateTime fechaInicio, LocalDateTime fechaFin, EspacioFisico espacioFisico) {
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.espacioFisico = espacioFisico;
	}

	public LocalDateTime getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDateTime fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDateTime getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDateTime fechaFin) {
		this.fechaFin = fechaFin;
	}

	public EspacioFisico getEspacioFisico() {
		return espacioFisico;
	}

	public void setEspacioFisico(EspacioFisico espacioFisico) {
		this.espacioFisico = espacioFisico;
	}
	
	@Override
	public String toString() {
		return "Ocupacion [fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", \n\t- espacioFisico="
				+ (espacioFisico != null ? espacioFisico.getNombre() : "null") + "]";
	}
	

}
