package eventos.modelo;

import java.util.LinkedList;
import java.util.List;


import repositorio.Identificable;

public class EspacioFisico implements Identificable{
	
	private String id;
	private String nombre;
	private String propietario;
	private int capacidad;
	private String direccion;
	private double longitud;
	private double latitud;
	private List<PuntoDeInteres> puntosDeInteres = new LinkedList<PuntoDeInteres>();	
	private String descripcion;
	private EstadoEspacioFisico estado;
	
	
	public EspacioFisico() {
		
	}
	
	public EspacioFisico(String nombre, String propiertario, int capacidad, String direccion, double longitud, double latitud, String descripcion) {
		this.nombre = nombre;
		this.propietario = propiertario;
		this.capacidad = capacidad;
		this.descripcion = descripcion;
		this.longitud = longitud;
		this.latitud = latitud;
		this.direccion = direccion;
		this.estado = EstadoEspacioFisico.ACTIVO;			
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

	public String getPropietario() {
		return propietario;
	}

	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public List<PuntoDeInteres> getPuntosDeInteres() {
		return puntosDeInteres;
	}

	public void setPuntosDeInteres(List<PuntoDeInteres> puntosDeInteres) {
		this.puntosDeInteres = puntosDeInteres;
	}
	
	public void addPuntosDeInteres(List<PuntoDeInteres> puntosDeInteres) {
		this.puntosDeInteres.addAll(puntosDeInteres);
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public EstadoEspacioFisico getEstado() {
		return estado;
	}

	public void setEstado(EstadoEspacioFisico estado) {
		this.estado = estado;
	}
	
	@Override
	public String toString() {
		return "EspacioFisico [id=" + id + ", nombre=" + nombre + ", propietario=" + propietario + ", capacidad="
				+ capacidad + ", direccion=" + direccion + ", longitud=" + longitud + ", latitud=" + latitud + ", descripcion=" + descripcion + ", estado=" + estado+ "]";
	}
	
	
}
