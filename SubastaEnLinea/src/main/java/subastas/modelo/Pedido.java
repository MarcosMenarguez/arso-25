package subastas.modelo;

import java.io.Serializable;
import java.util.List;

import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

import repositorio.Identificable;


public class Pedido implements Serializable, Identificable{
	
	@BsonId
	@BsonRepresentation(BsonType.OBJECT_ID) 	
	private String id;
	private String comprador;
	private List<String> articulos;
	private Direccion infoEnvio;
	private MetodoPago infoPago;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getComprador() {
		return comprador;
	}
	public void setComprador(String comprador) {
		this.comprador = comprador;
	}
	public List<String> getArticulos() {
		return articulos;
	}
	public void setArticulos(List<String> articulos) {
		this.articulos = articulos;
	}
	public Direccion getInfoEnvio() {
		return infoEnvio;
	}
	public void setInfoEnvio(Direccion infoEnvio) {
		this.infoEnvio = infoEnvio;
	}
	public MetodoPago getInfoPago() {
		return infoPago;
	}
	public void setInfoPago(MetodoPago infoPago) {
		this.infoPago = infoPago;
	}
	
	
	

}
