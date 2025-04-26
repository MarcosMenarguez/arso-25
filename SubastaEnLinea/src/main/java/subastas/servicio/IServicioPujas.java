package subastas.servicio;

import java.util.List;

import javax.ejb.Local;

import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import subastas.modelo.Puja;


@Local
public interface IServicioPujas {

	public Puja consultarPuja(String id)  throws RepositorioException, EntidadNoEncontrada;
	public String pujar(Puja puja) throws RepositorioException ;
	public void eliminarPuja(String id) throws RepositorioException, EntidadNoEncontrada;
	public List<PujaResumen> listarPujas(String articulo)  throws RepositorioException;
}
