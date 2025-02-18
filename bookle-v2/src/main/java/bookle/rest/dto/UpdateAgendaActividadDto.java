package bookle.rest.dto;

import java.util.LinkedList;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UpdateAgendaActividadDto {

	private LinkedList<DiaAgendaRequestDto> agenda;
	
	public UpdateAgendaActividadDto() {
		
	}
	
	public LinkedList<DiaAgendaRequestDto> getAgenda() {
		return agenda;
	}
	
	public void setAgenda(LinkedList<DiaAgendaRequestDto> agenda) {
		this.agenda = agenda;
	}
}
