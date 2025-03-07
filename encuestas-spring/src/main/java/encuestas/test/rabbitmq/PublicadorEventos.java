package encuestas.test.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PublicadorEventos {

	@Autowired
    private RabbitTemplate rabbitTemplate;

    public void emitirEvento(Object evento) {
        rabbitTemplate.convertAndSend(
          RabbitMQConfig.EXCHANGE_NAME, 
          RabbitMQConfig.BINDING_KEY, 
          evento);
    }
}
