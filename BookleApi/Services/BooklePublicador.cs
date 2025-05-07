using System.Text;
using RabbitMQ.Client;

namespace Bookle.Publicador{


    public interface IPublicadorMensajes{
        Task Publicar(string exchange, string routingkey, string mensaje);
    }

    public class RabbitMQPublicador : IPublicadorMensajes
    {
        private readonly IConnection _connection;
        private readonly IChannel _channel;

        public RabbitMQPublicador(IConnection connection, IChannel channel){
            _connection = connection;
            _channel = channel;
        }

        public static async Task<RabbitMQPublicador> CreateAsync(){
            ConnectionFactory factory = new ConnectionFactory();
            factory.Uri = new Uri("amqp://qrqmckxn:3qtkxu-9-4myj2rg-smYUyccm1_B-Hx9@rat-01.rmq2.cloudamqp.com:5672/qrqmckxn");

            var connection = await factory.CreateConnectionAsync();
            var channel = await connection.CreateChannelAsync();

            return new RabbitMQPublicador(connection,channel);

        }



        public Task Publicar(string exchange, string routingkey, string mensaje)
        {
            var body = Encoding.UTF8.GetBytes(mensaje);
            var props = new BasicProperties();

            _channel.BasicPublishAsync(exchange, routingkey, false, props, body);
            return Task.CompletedTask;
        }
    }



}