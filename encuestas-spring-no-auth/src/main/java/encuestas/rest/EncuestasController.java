package encuestas.rest;

import java.net.URI;
import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import encuestas.modelo.Encuesta;
import encuestas.rest.dto.EncuestaDto;
import encuestas.rest.dto.NuevaEncuestaDto;
import encuestas.servicio.EncuestaResumen;
import encuestas.servicio.IServicioEncuestas;

@RestController
@RequestMapping("/encuestas")
public class EncuestasController implements EncuestasApi {

	private IServicioEncuestas servicio;
	
	@Autowired
	private PagedResourcesAssembler<EncuestaResumen> pagedResourcesAssembler;
	
	@Autowired
	private EncuestaResumenAssembler encuestaResumenAssembler;


	@Autowired
	public EncuestasController(IServicioEncuestas servicio) {
		this.servicio = servicio;
	}

	@PostMapping
	public ResponseEntity<Void> createEncuesta(
			 @Valid @RequestBody NuevaEncuestaDto nuevaEncuesta) throws Exception {
	 
		String id = this.servicio.crear(nuevaEncuesta.getTitulo(),
				nuevaEncuesta.getInstrucciones(),
				LocalDateTime.parse(nuevaEncuesta.getApertura()),
				LocalDateTime.parse(nuevaEncuesta.getCierre()),
				nuevaEncuesta.getOpciones());
	 
		 // Construye la URL completa del nuevo recurso
		 URI nuevaURL = ServletUriComponentsBuilder.fromCurrentRequest()
				 			.path("/{id}").buildAndExpand(id).toUri();
		 
		 return ResponseEntity.created(nuevaURL).build();
	}
	
	/*
	
	@GetMapping("/{id}")
	public EncuestaDto getEncuestaById(@PathVariable String id) throws Exception {
	 
		Encuesta encuesta = this.servicio.getEncuesta(id);
	 
		 // retorna el DTO
		 return EncuestaDto.fromEntity(encuesta);	 
	}
	
	*/
	
	@GetMapping("/{id}")
	public EntityModel<EncuestaDto> getEncuestaById(@PathVariable String id) throws Exception {
		 
		Encuesta encuesta = this.servicio.getEncuesta(id);
		EncuestaDto encuestaDto = EncuestaDto.fromEntity(encuesta);
		 
		// Envolver el DTO con EntityModel y agregar enlace self
		EntityModel<EncuestaDto> model = EntityModel.of(encuestaDto);
		model.add(
			 WebMvcLinkBuilder.linkTo(
					 WebMvcLinkBuilder
					 	.methodOn(EncuestasController.class)
					 	.getEncuestaById(id))
				 .withSelfRel());
		return model;	 
	}
	
	
	/*
	@GetMapping
	public List<EncuestaResumen> getEncuestas() throws Exception {

		List<EncuestaResumen> encuestas = this.servicio.getListadoResumen();

		return encuestas;
	}
	*/
	
	/*
	@GetMapping
	public Page<EncuestaResumen> getEncuestasPaginado(Pageable paginacion) throws Exception {
	 
		return this.servicio.getListadoPaginado(paginacion);
	}
	*/
	
	/*
	@GetMapping
	public PagedModel<EntityModel<EncuestaResumen>> getEncuestasPaginado(Pageable paginacion) throws Exception {
		 
		 Page<EncuestaResumen> resultado = this.servicio.getListadoPaginado(paginacion);
		 
		 return this.pagedResourcesAssembler.toModel(resultado);
	}
	*/
	
	@GetMapping
	public PagedModel<EntityModel<EncuestaResumen>> getEncuestasPaginado(Pageable paginacion) throws Exception {
		 
		 Page<EncuestaResumen> resultado = this.servicio.getListadoPaginado(paginacion);
		 
		 return this.pagedResourcesAssembler.toModel(resultado, encuestaResumenAssembler);
	}
	
	
	
}