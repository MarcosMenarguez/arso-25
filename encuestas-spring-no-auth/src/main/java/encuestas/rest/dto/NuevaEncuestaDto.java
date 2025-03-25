package encuestas.rest.dto;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


public class NuevaEncuestaDto {

	@NotNull
    private String titulo;
    private String instrucciones;
    private List<String> opciones;
    
    @NotNull
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}", 
    	message = "Formato de fecha inválido. 'yyyy-MM-ddTHH:mm:ss'")
    private String apertura;
    private String cierre;

    public NuevaEncuestaDto() { // POJO
    
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

    public void setInstrucciones(String instrucciones) {
        this.instrucciones = instrucciones;
    }

    public List<String> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<String> opciones) {
        this.opciones = opciones;
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

}

