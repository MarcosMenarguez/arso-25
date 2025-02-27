package encuestas.rest.dto;

import java.util.List;

import encuestas.modelo.Encuesta;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO de la entidad Encuesta")
public class EncuestaDto {

    @Schema(description = "Identificador de la encuesta")
    private String id;
    @Schema(description = "Título de la encuesta")
    private String titulo;
    @Schema(description = "Instrucciones de uso")
    private String instrucciones;
    @Schema(description = "Fecha de apertura")
    private String apertura;
    @Schema(description = "Fecha de cierre")
    private String cierre;
    @Schema(description = "Opciones de la encuesta")
    private List<String> opciones;

    public EncuestaDto() {
    }

    public EncuestaDto(String id, String titulo, String instrucciones, String apertura, String cierre,
            List<String> opciones) {
        this.id = id;
        this.titulo = titulo;
        this.instrucciones = instrucciones;
        this.apertura = apertura;
        this.cierre = cierre;
        this.opciones = opciones;
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

    public String getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(String descripcion) {
        this.instrucciones = descripcion;
    }

    public String getApertura() {
		return apertura;
	}
    
    public void setApertura(String apertura) {
		this.apertura = apertura;
	}
    
    public String getCierre() {
		return cierre;
	}
    
    public void setCierre(String cierre) {
		this.cierre = cierre;
	}
    
    public List<String> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<String> opciones) {
        this.opciones = opciones;
    }

    public static EncuestaDto fromEntity(Encuesta encuesta) {
        return new EncuestaDto(encuesta.getId(), encuesta.getTitulo(), encuesta.getInstrucciones(),
                encuesta.getApertura().toString(), encuesta.getCierre().toString(),
                encuesta.getOpciones().stream().map(opcion -> opcion.getTexto()).toList());
    }
}
