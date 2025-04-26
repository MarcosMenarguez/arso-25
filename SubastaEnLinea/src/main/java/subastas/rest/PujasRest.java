package subastas.rest;

import java.net.URI;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import subastas.modelo.Puja;
import subastas.servicio.IServicioPujas;
import subastas.servicio.PujaResumen;;

@Path("pujas")
@Stateless
public class PujasRest {
	@EJB(beanName="ServicioPujas")
	private IServicioPujas servicio;
	
	@Context
	private UriInfo uriInfo;
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPuja(@PathParam("id") String id) throws Exception {		
		
		return Response.status(Response.Status.OK).entity(servicio.consultarPuja(id)).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response pujar(Puja puja) throws Exception {
		
			String id = servicio.pujar(puja);
			URI nuevaURL = this.uriInfo.getAbsolutePathBuilder().path(id).build();
			return Response.created(nuevaURL).build();
			
				
	}

	@DELETE
	@Path("/{id}")
	public Response eliminarPuja(@PathParam("id")String id) throws Exception {
		
		servicio.eliminarPuja(id);
		
		return Response.status(Response.Status.NO_CONTENT).build();
		
		
	}

	@GET
	@Path("/buscar/{articulo}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response listarPujas(@PathParam("articulo") String articulo) throws Exception {
		
		List<PujaResumen> resultado = servicio.listarPujas(articulo);
		
		
		LinkedList<ResumenExtendido> extendido = new LinkedList<>();
		
		for (PujaResumen puja : resultado) {
			
				ResumenExtendido resumenExtendido = new ResumenExtendido();
				resumenExtendido.setResumen(puja);
				
				// Construir la URL
				String idPuja = puja.getId();
				URI nuevaURL = this.uriInfo.getBaseUriBuilder().path("pujas").path(idPuja).build();
				resumenExtendido.setUrl(nuevaURL.toString());

				extendido.add(resumenExtendido);
		}
		
		return Response.ok(extendido).build();
	}


}
