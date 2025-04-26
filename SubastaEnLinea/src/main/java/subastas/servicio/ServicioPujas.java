package subastas.servicio;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import repositorio.Repositorio;
import repositorio.RepositorioException;
import repositorio.EntidadNoEncontrada;
import subastas.modelo.Puja;


@Stateless
public class ServicioPujas implements IServicioPujas{
	
	@EJB(beanName="RepositorioPujas")
	private Repositorio<Puja, String> repositorio;

	@Override
	public String pujar(Puja puja) throws RepositorioException {
		
			return repositorio.add(puja);
		
		
	}

	@Override
	public void eliminarPuja(String id) throws RepositorioException, EntidadNoEncontrada {
		
			Puja p =repositorio.getById(id);
			repositorio.delete(p);
		
		
	}

	@Override
	public List<PujaResumen> listarPujas(String articulo) throws RepositorioException {

		List<PujaResumen> resultado = new ArrayList<PujaResumen>();
		List<Puja> pujas;
			pujas = repositorio.getAll();
			for(Puja p:pujas) {
				if(p.getArticulo().equals(articulo)) {
					PujaResumen pr = new PujaResumen();
					pr.setArticulo(p.getArticulo());
					pr.setId(p.getId());
					pr.setImporte(p.getImporte());
					pr.setPujador(p.getPujador());
					resultado.add(pr);
				}
			}
		
		
		return resultado;
		
	}

	@Override
	public Puja consultarPuja(String id) throws RepositorioException, EntidadNoEncontrada {
		return repositorio.getById(id);
	}


}
