package encuestas.repositorio;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import encuestas.modelo.Encuesta;

@NoRepositoryBean
public interface RepositorioEncuestas extends PagingAndSortingRepository<Encuesta, String> {
	
}
