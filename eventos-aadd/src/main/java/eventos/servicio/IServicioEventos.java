package eventos.servicio;

import java.time.LocalDateTime;
import java.util.List;

import eventos.modelo.CategoriaEvento;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;

public interface IServicioEventos {

	String altaEvento(String nombre, String descripcion, String organizador, CategoriaEvento categoria,
			LocalDateTime fechaInicio, LocalDateTime fechaFin, int plazas, String idEspacioFisico)
			throws RepositorioException, EntidadNoEncontrada;

	void modificarEvento(String id, String descripcion, LocalDateTime fechaInicio, LocalDateTime fechaFin, int plazas,
			String idEspacioFisico) throws RepositorioException, EntidadNoEncontrada;

	void cancelarEvento(String id) throws RepositorioException, EntidadNoEncontrada;

	List<EventoResumen> eventosDelMes(int mes, int anyo) throws RepositorioException;
}
