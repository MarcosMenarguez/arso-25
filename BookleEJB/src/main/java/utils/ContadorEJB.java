package utils;

import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;

@Singleton
public class ContadorEJB {
	
	private int contador;
	
	@PostConstruct
	private void init() {
		contador = 0;
	}
	
	@Lock(LockType.READ)
	private int valorActual() {
		return contador;
	}
	
	@Lock(LockType.WRITE)
	private int siguienteValor() {
		return ++contador;
	}

}
