package encuestas.test.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumidorEventos {

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void handleEvent(Message mensaje) {
        System.out.println("Mensaje recibido: " + mensaje);
        
        String body = new String(mensaje.getBody());
        System.out.println("Evento:" + body);
    }
}
