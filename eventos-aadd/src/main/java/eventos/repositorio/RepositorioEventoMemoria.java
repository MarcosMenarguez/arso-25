package eventos.repositorio;

import java.time.LocalDateTime;

import eventos.modelo.CategoriaEvento;
import eventos.modelo.EspacioFisico;
import eventos.modelo.Evento;
import repositorio.FactoriaRepositorios;
import repositorio.Repositorio;
import repositorio.RepositorioMemoria;

public class RepositorioEventoMemoria extends RepositorioMemoria<Evento> {

	public RepositorioEventoMemoria() {
		
		// Datos iniciales
		
		try {
			Repositorio<EspacioFisico, String> repositorioEspacio = FactoriaRepositorios
					.getRepositorio(EspacioFisico.class);
					
			EspacioFisico ef1 = repositorioEspacio.getById("1");
			
			
			Evento ev1 = new Evento("Presentación libro 'Las cosas que pasan", "se va a presentar la nueva novela de..",
					"Alejo Acerco", 30, CategoriaEvento.CULTURALES, LocalDateTime.now().plusHours(2), LocalDateTime.now().plusHours(4), ef1);
			
			this.add(ev1);
			
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
