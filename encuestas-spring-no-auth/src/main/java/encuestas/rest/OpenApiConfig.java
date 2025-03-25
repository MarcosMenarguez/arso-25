package encuestas.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI openApi() {
		return new OpenAPI()
				.info(new Info()
						.title("Servicio de Encuestas")
						.description("Gestión de escuestas")
						.version("1.0.0"));
	}
}