package rabbitmq;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class Consumidor {

	public static void main(String[] args) throws Exception {

		String uri = System.getenv("RABBITMQ_URI");
		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setUri(uri);

		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
	
		boolean autoAck = false;
		channel.basicConsume("arso-test", autoAck, "arso-consumidor", 
		  new DefaultConsumer(channel) {
		    @Override
		    public void handleDelivery(String consumerTag, Envelope envelope, 		
		    AMQP.BasicProperties properties,byte[] body) throws IOException {
		        
		        String routingKey = envelope.getRoutingKey();
		        String contentType = properties.getContentType();
		        long deliveryTag = envelope.getDeliveryTag();

		        String contenido = new String(body);
		        System.out.println(contenido);
		        
		        // Confirma el procesamiento
		        channel.basicAck(deliveryTag, false);
		    }
		});
		
		
		System.out.println("consumidor esperando ...");
	
	}
}
