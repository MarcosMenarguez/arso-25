package encuestas;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import encuestas.modelo.Encuesta;
import encuestas.repositorio.RepositorioEncuestas;

public class PruebaRepositorio {

	public static void main(String[] args) {
		
		ConfigurableApplicationContext contexto = SpringApplication.run(EncuestasSpringApplication.class, args);

		Encuesta encuesta = new Encuesta("Test 1", "Instrucciones", LocalDateTime.now().minusDays(1),
				LocalDateTime.now().plusDays(1), Arrays.asList("Opción 1", "Opción 2"));
		
		RepositorioEncuestas repositorio = contexto.getBean(RepositorioEncuestas.class);
		
		String id = repositorio.save(encuesta).getId();
		
		System.out.println("Id: " + id);
		
		contexto.close();
	}
}
