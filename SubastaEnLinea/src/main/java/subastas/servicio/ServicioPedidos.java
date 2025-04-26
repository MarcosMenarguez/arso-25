package subastas.servicio;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.ejb.Stateful;

import subastas.modelo.Direccion;
import subastas.modelo.MetodoPago;
import subastas.modelo.Pedido;
import repositorio.Repositorio;
import repositorio.RepositorioException;

@Stateful
public class ServicioPedidos implements IServicioPedidos {

	@EJB(beanName = "RepositorioPedidos")
	private Repositorio<Pedido, String> repositorio;

	private String comprador;
	private List<String> articulos;
	private Direccion datosEnvio;
	private MetodoPago metodoPago;

	public ServicioPedidos() {
		articulos = new ArrayList<String>();
	}

	@Override
	public void comenzarPedido(String usuario) {
		this.comprador = usuario;

	}

	@Override
	public void addArticulo(String articulo) {
		articulos.add(articulo);

	}

	@Override
	public void addDatosEnvio(Direccion direccionEnvio) {
		this.datosEnvio = direccionEnvio;

	}

	@Override
	public void addInformacionPago(MetodoPago infoPago) {
		this.metodoPago = infoPago;

	}

	@Override
	@Remove
	public void confirmarPedido() throws RepositorioException {
		Pedido p = new Pedido();
		p.setArticulos(articulos);
		p.setComprador(comprador);
		p.setInfoEnvio(datosEnvio);
		p.setInfoPago(metodoPago);

		repositorio.add(p);

	}

}
