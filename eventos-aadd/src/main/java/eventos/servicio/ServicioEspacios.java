package eventos.servicio;

import java.time.LocalDateTime;
import java.util.List;

import eventos.modelo.EspacioFisico;
import eventos.modelo.EstadoEspacioFisico;
import eventos.modelo.Evento;
import eventos.modelo.PuntoDeInteres;
import repositorio.EntidadNoEncontrada;
import repositorio.FactoriaRepositorios;
import repositorio.Repositorio;
import repositorio.RepositorioException;

public class ServicioEspacios implements IServicioEspacios {

	private Repositorio<EspacioFisico, String> repositorioEspacio = FactoriaRepositorios
			.getRepositorio(EspacioFisico.class);
	
	@Override
	public String altaEspacioFisico(String nombre, String propietario, int capacidad, String direccion, double longitud,
			double latitud, String descripcion) throws RepositorioException, EntidadNoEncontrada {

		if (nombre == null || nombre.isBlank())
			throw new IllegalArgumentException("nombre: no debe ser nulo o vacío.");
		if (propietario == null || propietario.isBlank())
			throw new IllegalArgumentException("propietario: no debe ser nulo o vacío.");
		if (capacidad <= 0)
			throw new IllegalArgumentException("capacidad: debe ser mayor que cero.");
		if (direccion == null || direccion.isBlank())
			throw new IllegalArgumentException("dirección: no debe ser nula o vacía.");

		EspacioFisico espacioFisico = new EspacioFisico(nombre, propietario, capacidad, direccion, longitud, latitud,
				descripcion);
		String id = repositorioEspacio.add(espacioFisico);

		return id;
	}

	@Override
	public void asignarPuntosDeInteres(String id, List<PuntoDeInteres> puntosDeInteres)
			throws RepositorioException, EntidadNoEncontrada {

		if (id == null || id.isBlank())
			throw new IllegalArgumentException("id: no debe ser nulo o vacío.");
		if (puntosDeInteres == null || puntosDeInteres.isEmpty())
			throw new IllegalArgumentException("puntosDeInterés: no debe ser una colección nula o vacía.");

		EspacioFisico espacioFisico = repositorioEspacio.getById(id);
		espacioFisico.setPuntosDeInteres(puntosDeInteres);
		repositorioEspacio.update(espacioFisico);

	}

	@Override
	public void modificarEspacioFisico(String id, String nombre, int capacidad, String descripcion)
			throws RepositorioException, EntidadNoEncontrada {

		if (id == null || id.isBlank())
			throw new IllegalArgumentException("id: no debe ser nulo o vacío.");
		if (nombre == null || nombre.isBlank())
			throw new IllegalArgumentException("nombre: no debe ser nulo o vacío.");
		if (capacidad <= 0)
			throw new IllegalArgumentException("capacidad: debe ser mayor que cero.");

		EspacioFisico espacioFisico = repositorioEspacio.getById(id);

		espacioFisico.setNombre(nombre);
		espacioFisico.setCapacidad(capacidad);
		espacioFisico.setDescripcion(descripcion);
		repositorioEspacio.update(espacioFisico);

	}

	@Override
	public void bajaEspacioFisico(String id) throws RepositorioException, EntidadNoEncontrada {
		if (id == null || id.isBlank())
			throw new IllegalArgumentException("id: no debe ser nulo o vacío.");

		EspacioFisico espacioFisico = repositorioEspacio.getById(id);

		// TODO: comprobar la regla de negocio
		
		espacioFisico.setEstado(EstadoEspacioFisico.CERRADO_TEMPORALMENTE);
		repositorioEspacio.update(espacioFisico);

	}

	@Override
	public void activarEspacioFisico(String id) throws RepositorioException, EntidadNoEncontrada {
		
		if (id == null || id.isBlank())
			throw new IllegalArgumentException("id: no debe ser nulo o vacío.");

		EspacioFisico espacioFisico = repositorioEspacio.getById(id);
		espacioFisico.setEstado(EstadoEspacioFisico.ACTIVO);
		repositorioEspacio.update(espacioFisico);

	}

	@Override
	public List<EspacioFisico> buscarEspaciosFisicosLibres(LocalDateTime fechaInicio, LocalDateTime fechaFin,
			int capacidadMin) throws RepositorioException, EntidadNoEncontrada {
		
		if (fechaInicio == null)
			throw new IllegalArgumentException("fechaInicio: no debe ser nula.");
		if (fechaFin == null)
			throw new IllegalArgumentException("fechaFin: no debe ser nula.");
		
		// TODO: obtener los espacios ocupados y filtrar
				
		return repositorioEspacio.getAll();
	}

}
