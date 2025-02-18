package bookle.rest;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import bookle.modelo.Actividad;
import bookle.modelo.DiaAgenda;
import bookle.modelo.Turno;
import bookle.rest.Listado.ResumenExtendido;
import bookle.rest.dto.ActividadResponseDto;
import bookle.rest.dto.CreateActividadDto;
import bookle.rest.dto.DiaAgendaRequestDto;
import bookle.rest.dto.UpdateAgendaActividadDto;
import bookle.rest.dto.UpdateDescriptionActividadDto;
import bookle.servicio.ActividadResumen;
import bookle.servicio.IServicioBookle;
import servicio.FactoriaServicios;

@Path("actividades")
public class ControladorBookle {

	private IServicioBookle servicio = FactoriaServicios.getServicio(IServicioBookle.class);

	@Context
	private UriInfo uriInfo;

	// http://localhost:8080/api/actividades/1

	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response getActividad(@PathParam("id") String id) throws Exception {

		Actividad actividad = servicio.recuperar(id);
		
		ActividadResponseDto dto = new ActividadResponseDto(actividad.getId(),
				actividad.getTitulo(),
				actividad.getDescripcion(),
				actividad.getProfesor(), 
				actividad.getEmail(), 
				actividad.getAgenda().stream()
					.map(dia -> dia.getFecha().toString()).collect(Collectors.toList()));
		
		return Response.status(Response.Status.OK).entity(dto).build();
	}

	// curl -i -X PATCH -H "Content-type: application/json" -d "{'titulo': 'Reunión', 'descripcion': 'zoom'}"  http://localhost:8080/api/actividades/1
	
	@PATCH
	@Path("/{id}")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response updateDescripcion(@PathParam("id") String id, UpdateDescriptionActividadDto dto) throws Exception {
		
		servicio.actualizarDescripcion(id, 
				dto.getTitulo(),
				dto.getDescripcion(),
				dto.getEmail());
		
		return Response.status(Response.Status.NO_CONTENT).build();
	}

	// curl -i -X PUT -H "Content-type: application/json" -d "{'agenda': [{'fecha': '2025-04-30', 'turnos': ['10h', '11h'] } ] }"  http://localhost:8080/api/actividades/1/agenda

	@PUT
	@Path("/{id}/agenda")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response updateAgenda(@PathParam("id") String id, UpdateAgendaActividadDto dto) throws Exception {
		
		// Transforma DTO al modelo del dominio
		LinkedList<DiaAgenda> diasAgenda = new LinkedList<DiaAgenda>();
		for (DiaAgendaRequestDto diaDto : dto.getAgenda()) {
			DiaAgenda dia = new DiaAgenda();
			dia.setFecha(LocalDate.parse(diaDto.getFecha()));
			LinkedList<Turno> turnos = new LinkedList<Turno>();
			for (String turnoDto : diaDto.getTurnos()) {
				Turno turno = new Turno();
				turno.setHorario(turnoDto);
				turnos.add(turno);
			}
			dia.setTurno(turnos);
			diasAgenda.add(dia);
		}
		
		servicio.actualizarAgenda(id, diasAgenda);
		
		return Response.status(Response.Status.NO_CONTENT).build();
	}

	
	// curl -i -X DELETE http://localhost:8080/api/actividades/1

	@DELETE
	@Path("/{id}")
	public Response removeActividad(@PathParam("id") String id) throws Exception {
		servicio.borrar(id);
		return Response.status(Response.Status.NO_CONTENT).build();
	}

	// curl -i -X POST -H "Content-type: application/json" -d "{'titulo': 'Entrevistas', 'descripcion': 'zoom', 'profesor': 'Juan', 'email': 'juan@um.es'}"  http://localhost:8080/api/actividades/

	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response createActividad(CreateActividadDto dto) throws Exception {
		
		String id = servicio.crear(
				dto.getTitulo(),
				dto.getDescripcion(),
				dto.getProfesor(),
				dto.getEmail());
		
		URI nuevaURL = this.uriInfo.getAbsolutePathBuilder().path(id).build();
		return Response.created(nuevaURL).build();
	}

	// curl -i -X POST --data "alumno=Pepe&email=pepe@um.es" http://localhost:8080/api/actividades/1/agenda/2025-02-14/turnos/1/reserva

	@POST
	@Path("/{id}/agenda/{fecha}/turnos/{indice}/reserva")
	public Response reservar(@PathParam("id") String id, @PathParam("fecha") String fecha,
			@PathParam("indice") int indice, @FormParam("alumno") String alumno, @FormParam("email") String email)
			throws Exception {
		LocalDate fechaDate = null;
		try {
			fechaDate = LocalDate.parse(fecha);
		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException(e);
		}
		servicio.reservar(id, fechaDate, indice, alumno, email);

		return Response.status(Response.Status.NO_CONTENT).build();
	}

	// curl -i -H "Accept: application/xml" http://localhost:8080/api/actividades
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response getListadoActividades() throws Exception {
		
		List<ActividadResumen> resultado = servicio.recuperarTodas();
		
		LinkedList<ResumenExtendido> extendido = new LinkedList<>();
		for (ActividadResumen actividadResumen : resultado) {
			ResumenExtendido resumenExtendido = new ResumenExtendido();
			resumenExtendido.setResumen(actividadResumen);

			// Construir la URL
			String id = actividadResumen.getId();
			URI nuevaURL = this.uriInfo.getAbsolutePathBuilder().path(id).build();
			resumenExtendido.setUrl(nuevaURL.toString());

			extendido.add(resumenExtendido);
		}
		Listado listado = new Listado();
		listado.setActividad(extendido);
		return Response.ok(listado).build();
	}

}