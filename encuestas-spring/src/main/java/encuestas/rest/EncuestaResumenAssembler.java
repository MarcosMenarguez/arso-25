package encuestas.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import encuestas.servicio.EncuestaResumen;

@Component
public class EncuestaResumenAssembler implements RepresentationModelAssembler<EncuestaResumen, EntityModel<EncuestaResumen>> {

    @Override
    public EntityModel<EncuestaResumen> toModel(EncuestaResumen encuestaResumen) {
    	try {
    		
	        EntityModel<EncuestaResumen> resultado = EntityModel.of(encuestaResumen, 
	                linkTo(methodOn(EncuestasController.class).getEncuestaById(encuestaResumen.getId())).withSelfRel());
	        return resultado;
	    } 
    	catch(Exception e) {
	    	return  EntityModel.of(encuestaResumen);
	    }
    }
}