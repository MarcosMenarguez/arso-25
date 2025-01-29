package eventos.test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import eventos.modelo.CategoriaEvento;
import eventos.modelo.EspacioFisico;
import eventos.servicio.EventoResumen;
import eventos.servicio.IServicioEspacios;
import eventos.servicio.IServicioEventos;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import servicio.FactoriaServicios;

public class Programa {

	public static void main(String[] args) throws RepositorioException, EntidadNoEncontrada {

		IServicioEspacios servicioEF = FactoriaServicios.getServicio(IServicioEspacios.class);
		IServicioEventos servicioEventos = FactoriaServicios.getServicio(IServicioEventos.class);
		
		double latitud = 37.98807580360032;
		double longitud = -1.127559487130454;
		
		String idDM = servicioEF.altaEspacioFisico("Librería Diego Marín", "Diego Marín", 40,"Calle Merced, 9, 30001, Murcia", longitud, latitud, "Espacio de venta de libros con sala para pequeñas presentaciones");
		
		double lat2 = 37.99419535746544;
		double long2 = -1.1146515854671075;
		
		String idSM = servicioEF.altaEspacioFisico("Sala Mamba!",  "Mamberos S.A.", 300, "Carril Molino de Nelva, 10, 30007, Puente Tocinos, Murcia", long2, lat2, "Sala de conciertos...");
		
		List<EspacioFisico> espaciosMin100 = servicioEF.buscarEspaciosFisicosLibres(LocalDateTime.now(), LocalDateTime.now().plusDays(1), 100);
		System.out.println("Espacios con capacidad de hasta 100 "+espaciosMin100);
		
		List<EspacioFisico> espaciosMin20 = servicioEF.buscarEspaciosFisicosLibres(LocalDateTime.now(), LocalDateTime.now().plusDays(1), 20);
		System.out.println("Espacios con capacidad de hasta 20 "+espaciosMin20);

		
		servicioEventos.altaEvento("Presentación libro 'Las cosas que pasan", "se va a presentar la nueva novela de..",
				"Alejo Acerco", CategoriaEvento.CULTURALES, LocalDateTime.now().plusHours(2), LocalDateTime.now().plusHours(4), 30, idDM);
		
		List<EventoResumen> eventosEnero = servicioEventos.eventosDelMes(1, 2025);
		System.out.println("Eventos de enero de 2025 "+eventosEnero);
		
		List<EspacioFisico> espaciosLibres = servicioEF.buscarEspaciosFisicosLibres(LocalDateTime.now(), LocalDateTime.now().plusDays(1), 1);
		System.out.println("Espacios libres despuésde crear evento "+espaciosLibres);
		
		servicioEF.modificarEspacioFisico(idDM, "Librería Papelería Diego Marín", 40, "Librería histórica Murciana..");
		
		eventosEnero = servicioEventos.eventosDelMes(1, 2025);
		System.out.println("Eventos de enero de 2025 "+eventosEnero);

		

		System.out.println("fin.");

	}

}
