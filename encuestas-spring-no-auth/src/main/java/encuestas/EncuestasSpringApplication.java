package encuestas;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import encuestas.servicio.IServicioEncuestas;

@SpringBootApplication
public class EncuestasSpringApplication {

	public static void main(String[] args) {
		
		ConfigurableApplicationContext contexto = SpringApplication.run(EncuestasSpringApplication.class, args);

		IServicioEncuestas servicio = contexto.getBean(IServicioEncuestas.class);
		
		// Configura la encuesta
		
		String titulo = "Fecha del parcial";
		String instrucciones = "Llevar DNI";
		LocalDateTime apertura = LocalDateTime.now();
		LocalDateTime cierre = LocalDateTime.now().plusDays(1);
		List<String> opciones = Arrays.asList("Jueves", "Viernes");
		
		String id = servicio.crear(titulo, instrucciones, apertura, cierre, opciones);

		// contexto.close();
	}

}
