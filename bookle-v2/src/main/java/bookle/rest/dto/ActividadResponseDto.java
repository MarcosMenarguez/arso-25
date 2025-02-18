package bookle.rest.dto;

import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "actividad")
public class ActividadResponseDto {

	private String id;
	private String titulo;
	private String descripcion;
	private String profesor;
	private String email;
	private List<String> agenda = new LinkedList<>();
	
	public ActividadResponseDto() {}
	
	public ActividadResponseDto(String id, String titulo, String descripcion, String profesor, String email,
			List<String> agenda) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.profesor = profesor;
		this.email = email;
		this.agenda = agenda;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getProfesor() {
		return profesor;
	}

	public void setProfesor(String profesor) {
		this.profesor = profesor;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getAgenda() {
		return agenda;
	}

	public void setAgenda(List<String> agenda) {
		this.agenda = agenda;
	}
		
	
}
