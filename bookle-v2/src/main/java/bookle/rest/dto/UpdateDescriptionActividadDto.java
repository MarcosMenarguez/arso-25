package bookle.rest.dto;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UpdateDescriptionActividadDto {

	private String titulo; 
	private String descripcion; 
	private String email;

	public UpdateDescriptionActividadDto() {
		
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
}
