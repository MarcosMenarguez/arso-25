package pasarela.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class Pasarela {

	public static void main(String[] args) {
		SpringApplication.run(Pasarela.class, args);
	}
}
