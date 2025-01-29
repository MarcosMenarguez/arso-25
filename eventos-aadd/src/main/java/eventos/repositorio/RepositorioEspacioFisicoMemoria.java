package eventos.repositorio;

import eventos.modelo.EspacioFisico;
import repositorio.RepositorioMemoria;

public class RepositorioEspacioFisicoMemoria extends RepositorioMemoria<EspacioFisico> {

	public RepositorioEspacioFisicoMemoria() {
		
		// Datos iniciales
		
		double latitud = 37.98807580360032;
		double longitud = -1.127559487130454;
		
		EspacioFisico ef1 = new EspacioFisico("Librería Diego Marín", "Diego Marín", 40,"Calle Merced, 9, 30001, Murcia", longitud, latitud, "Espacio de venta de libros con sala para pequeñas presentaciones");
		
		this.add(ef1); // id=1
		
		double lat2 = 37.99419535746544;
		double long2 = -1.1146515854671075;
		
		EspacioFisico ef2 = new EspacioFisico("Sala Mamba!",  "Mamberos S.A.", 300, "Carril Molino de Nelva, 10, 30007, Puente Tocinos, Murcia", long2, lat2, "Sala de conciertos...");
		
		this.add(ef2); // id=2
	}
	
}
