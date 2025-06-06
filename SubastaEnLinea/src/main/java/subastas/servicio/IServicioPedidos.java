package subastas.servicio;

import javax.ejb.Remote;

import repositorio.RepositorioException;
import subastas.modelo.Direccion;
import subastas.modelo.MetodoPago;

@Remote
public interface IServicioPedidos {
	
	void comenzarPedido(String usuario);
	void addArticulo(String articulo);
	void addDatosEnvio(Direccion direccionEnvio);
	void addInformacionPago(MetodoPago infoPago);
	void confirmarPedido() throws RepositorioException;

}
