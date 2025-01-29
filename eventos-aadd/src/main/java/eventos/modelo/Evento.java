package eventos.modelo;

import java.time.LocalDateTime;


import repositorio.Identificable;

public class Evento implements Identificable{

	
	private String id;	
	private String nombre;
	private String descripcion;
	private String organizador;
	private int plazas;
	private boolean cancelado;
	private CategoriaEvento categoria;	
	private Ocupacion ocupacion;
	
	
	public Evento(){
		
	}
	
	public Evento(String nombre, String descripcion, String organizador, int plazas, CategoriaEvento categoria, LocalDateTime fechaInicio, LocalDateTime fechaFin, EspacioFisico espacioFisico) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.organizador = organizador;
		this.plazas = plazas;
		this.cancelado = false;
		this.categoria = categoria;
		this.ocupacion = new Ocupacion(fechaInicio, fechaFin, espacioFisico);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getOrganizador() {
		return organizador;
	}

	public void setOrganizador(String organizador) {
		this.organizador = organizador;
	}

	public int getPlazas() {
		return plazas;
	}

	public void setPlazas(int plazas) {
		this.plazas = plazas;
	}

	public boolean isCancelado() {
		return cancelado;
	}

	public void setCancelado(boolean cancelado) {
		this.cancelado = cancelado;
	}

	public CategoriaEvento getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaEvento categoria) {
		this.categoria = categoria;
	}

	public Ocupacion getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(Ocupacion ocupacion) {
		this.ocupacion = ocupacion;
	}
	
	public void editarOcupacion(LocalDateTime fechaInicio, LocalDateTime fechaFin, EspacioFisico ef) {
		if(ocupacion == null) {
			this.ocupacion = new Ocupacion(fechaInicio, fechaFin, ef);
		}
		else {
			this.ocupacion.setFechaInicio(fechaInicio);
			this.ocupacion.setFechaFin(fechaFin);
			this.ocupacion.setEspacioFisico(ef);
		}
	}
	
	@Override
	public String toString() {
		return "Evento [id=" + id + ", nombre=" + nombre + ", descripción=" + descripcion + ", organizador="
				+ organizador + ", plazas=" + plazas + ", cancelado=" + cancelado + ", categoria=" + categoria + ", ocupacion=" + ocupacion + "]";
	}
	
}
