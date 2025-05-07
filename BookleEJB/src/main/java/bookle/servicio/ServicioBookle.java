package bookle.servicio;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.jms.BytesMessage;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.TextMessage;

import bookle.modelo.Actividad;
import bookle.modelo.DiaAgenda;
import bookle.modelo.Reserva;
import bookle.modelo.Turno;
import repositorio.EntidadNoEncontrada;
import repositorio.Repositorio;
import repositorio.RepositorioException;

@Stateless
public class ServicioBookle implements IServicioBookle {

	@EJB(beanName = "RepositorioActividad")
	private Repositorio<Actividad, String> repositorio;

	@Resource(lookup = "java:global/jms/ConnectionFactory")
	private ConnectionFactory connectionFactoryRabbit;

	@Resource(lookup = "java:global/jms/Queue")
	private Queue destino;
	
	@Resource
	private TimerService timerService;

	@Override
	public String crear(Actividad actividad) throws RepositorioException {

		return repositorio.add(actividad);
	}

	@Override
	public void actualizar(Actividad actividad) throws RepositorioException, EntidadNoEncontrada {

		repositorio.update(actividad);
	}

	@Override
	public Actividad recuperar(String id) throws RepositorioException, EntidadNoEncontrada {

		return repositorio.getById(id);
	}

	@Override
	public void borrar(String id) throws RepositorioException, EntidadNoEncontrada {

		Actividad actividad = repositorio.getById(id);

		repositorio.delete(actividad);
	}

	@Override
	public boolean reservar(String id, LocalDate fecha, int indice, String alumno, String email)
			throws RepositorioException, EntidadNoEncontrada {

		if (id == null || id.isEmpty())
			throw new IllegalArgumentException("El identificador no debe nulo o vacío");

		if (fecha == null)
			throw new IllegalArgumentException("La fecha debe establecerse");

		if (indice < 1)
			throw new IllegalArgumentException("El primer turno tiene índice 1");

		if (alumno == null || alumno.isEmpty())
			throw new IllegalArgumentException("El nombre del alumno no debe ser vacío");

		// email es opcional

		Actividad actividad = repositorio.getById(id);

		DiaAgenda diaActividad = null;

		Optional<DiaAgenda> resultado = actividad.getAgenda().stream().filter(dia -> dia.getFecha().equals(fecha))
				.findFirst();

		if (resultado.isPresent() == false)
			throw new IllegalArgumentException("La fecha no esta en la agenda: " + fecha);
		else
			diaActividad = resultado.get();

		if (indice > diaActividad.getTurno().size())
			throw new IllegalArgumentException("No existe el turno " + indice + " para la fecha " + fecha);

		Turno turno = diaActividad.getTurno().get(indice - 1);

		if (turno.getReserva() != null)
			return false;

		Reserva reserva = new Reserva();
		reserva.setAlumno(alumno);
		reserva.setEmail(email);

		turno.setReserva(reserva);

		repositorio.update(actividad);

		return true;
	}

	@Override
	public List<ActividadResumen> recuperarTodas() throws RepositorioException {

		LinkedList<ActividadResumen> resultado = new LinkedList<>();

		for (String id : repositorio.getIds()) {
			try {
				Actividad actividad = recuperar(id);
				ActividadResumen resumen = new ActividadResumen();
				resumen.setId(actividad.getId());
				resumen.setTitulo(actividad.getTitulo());
				resumen.setProfesor(actividad.getProfesor());
				resultado.add(resumen);

			} catch (Exception e) {
				// No debe suceder
				e.printStackTrace(); // para depurar
			}
		}

		return resultado;
	}

	@Timeout
	public void expirado(Timer timer) {
		
		String mensaje = (String) timer.getInfo();
		System.out.println("Envío de mensaje "+mensaje);
	}
	
	@Override
	public void enviarMensaje(String mensaje) {
		timerService.createSingleActionTimer(2*60*1000, new TimerConfig(mensaje,true));
		try (JMSContext context = connectionFactoryRabbit.createContext()) {
			context.createProducer().send(destino, mensaje);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public String recibirMensaje() {
		try (JMSContext context = connectionFactoryRabbit.createContext()) {
			JMSConsumer consumer = context.createConsumer(destino);

			Message mensaje = consumer.receive(5000);

			if (mensaje != null) {
				if (mensaje instanceof TextMessage) {
					TextMessage tm = (TextMessage) mensaje;

					System.out.println("Mensaje recibido: " + tm.getText());
					return tm.getText();
				} else if (mensaje instanceof BytesMessage) {
					BytesMessage mb = (BytesMessage) mensaje;
					byte[] byteData = null;
					byteData = new byte[(int) mb.getBodyLength()];
					mb.readBytes(byteData);
					mb.reset();
					System.out.println("Mensaje reicibido: "+new String(byteData));
					return new String(byteData);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
