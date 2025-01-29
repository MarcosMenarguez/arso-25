package eventos.servicio;

import java.time.LocalDateTime;
import java.util.List;

import eventos.modelo.EspacioFisico;
import eventos.modelo.PuntoDeInteres;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;

public interface IServicioEspacios {

	String altaEspacioFisico(String nombre, String propietario, int capacidad, String direccion, double longitud,
			double latitud, String descripcion) throws RepositorioException, EntidadNoEncontrada;

	void asignarPuntosDeInteres(String id, List<PuntoDeInteres> puntosDeInteres)
			throws RepositorioException, EntidadNoEncontrada;

	void modificarEspacioFisico(String id, String nombre, int capacidad, String descripcion)
			throws RepositorioException, EntidadNoEncontrada;

	void bajaEspacioFisico(String id) throws RepositorioException, EntidadNoEncontrada;

	void activarEspacioFisico(String id) throws RepositorioException, EntidadNoEncontrada;

	List<EspacioFisico> buscarEspaciosFisicosLibres(LocalDateTime fechaInicio, LocalDateTime fechaFin, int capacidadMin)
			throws RepositorioException, EntidadNoEncontrada;

}
