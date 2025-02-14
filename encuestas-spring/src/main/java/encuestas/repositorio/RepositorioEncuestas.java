package encuestas.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import encuestas.modelo.Encuesta;

@NoRepositoryBean
public interface RepositorioEncuestas extends CrudRepository<Encuesta, String> {
	
}
