package encuestas.test.rabbitmq;

import java.time.LocalDateTime;

public class EjemploInfo {

	private String valor;
	private LocalDateTime fecha;
	
	public EjemploInfo() {
		// POJO
	}
	
	public EjemploInfo(String valor) {
		super();
		this.valor = valor;
		this.fecha = LocalDateTime.now();
	}
	
	public String getValor() {
		return valor;
	}
	
	public LocalDateTime getFecha() {
		return fecha;
	}

	@Override
	public String toString() {
		return "EjemploInfo [valor=" + valor + ", fecha=" + fecha + "]";
	}
	
}
