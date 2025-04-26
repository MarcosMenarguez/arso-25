package aplicacion;

import java.time.LocalDate;

import javax.naming.NamingException;


import subastas.modelo.Direccion;
import subastas.modelo.MetodoPago;
import subastas.servicio.IServicioPedidos;
import util.InitialContextUtil;

public class PedidosTest {

	public static void main(String[] args) {
			try {
				//System.out.println("Running on Java: " + System.getProperty("java.version"));
				IServicioPedidos servicio = (IServicioPedidos) InitialContextUtil.getInstance().lookup(
						"ejb:/SubastaEnLinea/ServicioPedidos!subastas.servicio.IServicioPedidos?stateful");
				
				servicio.comenzarPedido("maria del carmen");
				servicio.addArticulo("Reloj antiguo");
				servicio.addArticulo("Otomana");

				Direccion dir = new Direccion();
				dir.setCalle("Calle Mayor");
				dir.setNumero(12);
				dir.setCodigoPostal("30100");
				
				servicio.addDatosEnvio(dir);
				
				MetodoPago mp = new MetodoPago();
				
				mp.setCodigo("984378504978497855");
				mp.setProveedor("VISA");
				mp.setFechaCaducidad(LocalDate.of(2027, 5,31));
				
				servicio.addInformacionPago(mp);
				
				servicio.confirmarPedido();
				
				
			} catch (NamingException e) {
				e.printStackTrace();
			}
			
		
		
	}

}
