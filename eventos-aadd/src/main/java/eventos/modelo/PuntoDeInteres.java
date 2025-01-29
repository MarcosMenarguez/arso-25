package eventos.modelo;

public class PuntoDeInteres {
	
	private String nombre;	
	private String descripcion;	
	private double distancia;
	private String urlWikipedia;	
	
	public PuntoDeInteres() {
		
	}
	
	public PuntoDeInteres(String nombre, String descripcion, double distancia, String urlWikipedia) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.distancia = distancia;
		this.urlWikipedia = urlWikipedia; 
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

	public double getDistancia() {
		return distancia;
	}

	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}

	public String getUrlWikipedia() {
		return urlWikipedia;
	}

	public void setUrlWikipedia(String urlWikipedia) {
		this.urlWikipedia = urlWikipedia;
	}
	
}
