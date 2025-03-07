package rabbitmq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Productor {

	public static void main(String[] args) throws Exception {
		
		String uri = System.getenv("RABBITMQ_URI");
		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setUri(uri);

		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		String mensaje = "hola";

		channel.basicPublish("amq.direct", "arso", new AMQP.BasicProperties.Builder()
				// .contentType("application/json")
				.build(), mensaje.getBytes());
		
		channel.close();
		connection.close();
		
		System.out.println("fin.");

	}
}
