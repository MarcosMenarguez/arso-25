package encuestas.rest;

import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import encuestas.rest.dto.EncuestaDto;
import io.swagger.v3.oas.annotations.Operation;

public interface EncuestasApi {

	 @Operation(summary = "Obtener encuesta", description = "Obtiene una encuesta por su id")
	 @GetMapping("/{id}")
	 public EntityModel<EncuestaDto> getEncuestaById(@PathVariable String id) throws Exception;
	 
	 // ...
}
