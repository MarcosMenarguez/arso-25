package bookle.servicio;

import java.time.LocalDate;
import java.util.List;

import bookle.modelo.Actividad;
import bookle.modelo.DiaAgenda;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;

public interface IServicioBookle {

	/** 
	 * Metodo de alta de una actividad.
	 * 
	 * @param actividad debe ser valida respecto al modelo de dominio
	 * @return identificador de la actividad
	 */
	
	/**
	 * Metodo de alta de una actividad.
	 * 
	 * @param titulo título de la actividad (obligatorio)
	 * @param descripcion describe el objetivo de la actividad (opcional)
	 * @param profesor organizador de la actividad (obligatorio)
	 * @param email correo de contacto del organizador (opcional)
	 * @return identificador de la actividad
	 * 
	 */
	String crear(String titulo, String descripcion, String profesor, String email) throws RepositorioException;
	
	/**
	 * Actualiza la descripción de una actividad.
	 * @param id identificador de la actividad
	 * @param titulo título de la actividad (obligatorio)
	 * @param descripcion describe el objetivo de la actividad (opcional)
	 * @param email correo de contacto del organizador (opcional)
	 */
	
	void actualizarDescripcion(String id, String titulo, String descripcion, String email) throws RepositorioException, EntidadNoEncontrada;

	/**
	 * Actualiza la agenda de la actividad
	 * @param id identificador de la actividad
	 * @param agenda días y turnos en los que se desarrolla la actividad
	 * 
	 */
	void actualizarAgenda(String id, List<DiaAgenda> agenda) throws RepositorioException, EntidadNoEncontrada;
	
	/**
	 * Recupera una actividad utilizando el identificador. 	
	 */
	Actividad recuperar(String id)  throws RepositorioException, EntidadNoEncontrada;
	
	/**
	 * Elimina una actividad utilizando el identificador.
	 */
	void borrar(String id)  throws RepositorioException, EntidadNoEncontrada;
	
		
	/**
	 * Realiza la reserva de un turno de la actividad si no está ocupado.
	 * @rerturn true si se ha podido efectuar la reserva, falso si está ocupado
	 */
	boolean reservar(String idActividad, LocalDate fecha, int indiceTurno, String alumno, String email) throws RepositorioException, EntidadNoEncontrada;
	
	
	/**
	 * Retorna un resumen de todas las actividades.	
	 */
	List<ActividadResumen> recuperarTodas() throws RepositorioException;
}
