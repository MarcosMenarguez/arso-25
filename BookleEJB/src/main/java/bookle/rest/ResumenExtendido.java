package bookle.rest;

import bookle.servicio.ActividadResumen;

public class ResumenExtendido {
	private String url;
	private ActividadResumen resumen;
	// Métodos get y set
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public ActividadResumen getResumen() {
		return resumen;
	}
	public void setResumen(ActividadResumen resumen) {
		this.resumen = resumen;
	}
}
