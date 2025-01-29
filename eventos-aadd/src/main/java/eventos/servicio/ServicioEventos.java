package eventos.servicio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import eventos.modelo.CategoriaEvento;
import eventos.modelo.EspacioFisico;
import eventos.modelo.Evento;
import repositorio.EntidadNoEncontrada;
import repositorio.FactoriaRepositorios;
import repositorio.Repositorio;
import repositorio.RepositorioException;

public class ServicioEventos implements IServicioEventos{

	private Repositorio<Evento, String> repositorioEvento = FactoriaRepositorios.getRepositorio(Evento.class);
	private Repositorio<EspacioFisico, String> repositorioEspacio = FactoriaRepositorios.getRepositorio(EspacioFisico.class);

	@Override
	public String altaEvento(String nombre, String descripcion, String organizador, CategoriaEvento categoria,
			LocalDateTime fechaInicio, LocalDateTime fechaFin, int plazas, String idEspacioFisico)
			throws RepositorioException, EntidadNoEncontrada {
		
		if (nombre == null || nombre.isBlank())
			throw new IllegalArgumentException("nombre: no debe ser nulo o vacío.");
		
		if (organizador == null || organizador.isBlank())
			throw new IllegalArgumentException("organizador: no debe ser nulo o vacío.");
		if (categoria == null)
			throw new IllegalArgumentException("categoria: no debe ser nula.");
		if (fechaInicio == null)
			throw new IllegalArgumentException("fechaInicio: no debe ser nula.");
		if (fechaFin == null)
			throw new IllegalArgumentException("fechaFin: no debe ser nula.");
		if (plazas <= 0)
			throw new IllegalArgumentException("plazas: debe ser mayor que 0.");
		if (idEspacioFisico == null || idEspacioFisico.isBlank())
			throw new IllegalArgumentException("idEspacioFisico: no debe ser nulo o vacío.");

		EspacioFisico espacioFisico = repositorioEspacio.getById(idEspacioFisico);

		Evento evento = new Evento(nombre, descripcion, organizador, plazas, categoria, fechaInicio, fechaFin, espacioFisico);

		return repositorioEvento.add(evento);
	}

	@Override
	public void modificarEvento(String id, String descripcion, LocalDateTime fechaInicio, LocalDateTime fechaFin,
			int plazas, String idEspacioFisico) throws RepositorioException, EntidadNoEncontrada {


		if (id == null || id.isBlank())
			throw new IllegalArgumentException("id: no debe ser nulo o vacío.");
		if (fechaInicio == null)
			throw new IllegalArgumentException("fechaInicio: no debe ser nula.");
		if (fechaFin == null)
			throw new IllegalArgumentException("fechaFin: no debe ser nula.");
		if (plazas <= 0)
			throw new IllegalArgumentException("plazas: debe ser mayor que 0.");
		if (idEspacioFisico == null || idEspacioFisico.isBlank())
			throw new IllegalArgumentException("idEspacioFisico: no debe ser nulo o vacío.");
		
		Evento evento = repositorioEvento.getById(id);
		EspacioFisico ef = repositorioEspacio.getById(idEspacioFisico);

		evento.setDescripcion(descripcion);
		evento.setPlazas(plazas);
		evento.editarOcupacion(fechaInicio, fechaFin, ef);

		repositorioEvento.update(evento);
		
	}

	@Override
	public void cancelarEvento(String id) throws RepositorioException, EntidadNoEncontrada {
		if (id == null || id.isBlank())
			throw new IllegalArgumentException("id: no debe ser nulo o vacío.");

		Evento evento = repositorioEvento.getById(id);
		evento.setOcupacion(null);
		evento.setCancelado(true);
		repositorioEvento.update(evento);
		
	}

	@Override
	public List<EventoResumen> eventosDelMes(int mes, int anyo) throws RepositorioException {
		
		// FIXME: repositorio ad hoc
		
		YearMonth yearMonth = YearMonth.of(anyo, mes);
		LocalDate inicioMes = yearMonth.atDay(1);
		LocalDate finMes = yearMonth.atEndOfMonth();
		
		Predicate<Evento> regla = evento -> evento.getOcupacion() != null
				&& evento.getOcupacion().getFechaFin().toLocalDate().isAfter(inicioMes)
				&& evento.getOcupacion().getFechaInicio().toLocalDate().isBefore(finMes);
		
		List<Evento> eventosMes = repositorioEvento.getAll().stream().filter(regla).collect(Collectors.toList());


		LinkedList<EventoResumen> resumenEventos = new LinkedList<EventoResumen>();
		
		for (Evento evento : eventosMes) {
			EventoResumen resumen = new EventoResumen();
			resumen.setNombre(evento.getNombre());
			resumen.setDescripcion(evento.getDescripcion());
			resumen.setFechaInicio(evento.getOcupacion().getFechaInicio());
			resumen.setCategoria(evento.getCategoria());
			resumen.setNombreEspacio(evento.getOcupacion().getEspacioFisico().getNombre());
			resumen.setDireccion(evento.getOcupacion().getEspacioFisico().getDireccion());
			resumen.setPuntosDeInteres(
					evento.getOcupacion().getEspacioFisico().getPuntosDeInteres().stream().map(pi -> pi.getNombre()).collect(Collectors.toList())
			);
						
			resumenEventos.add(resumen);
		}

		return resumenEventos;
	}

}
