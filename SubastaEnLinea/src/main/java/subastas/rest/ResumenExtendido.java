package subastas.rest;

import java.io.Serializable;

import subastas.servicio.PujaResumen;

public class ResumenExtendido implements Serializable{
	
	private String url;
	private PujaResumen resumen;

	// Métodos get y set
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public PujaResumen getResumen() {
		return resumen;
	}
	public void setResumen(PujaResumen resumen) {
		this.resumen = resumen;
	}

}
