
using System.Text;
using RabbitMQ.Client;
using RabbitMQ.Client.Events;

namespace Bookle.Consumidor
{


    public class ConsumidorMensajes : BackgroundService
    {

        private IConnection _connection;
        private IChannel _channel;

        public override async Task StartAsync(CancellationToken cancellationToken)
        {
            ConnectionFactory factory = new ConnectionFactory();
            factory.Uri = new Uri("amqp://qrqmckxn:3qtkxu-9-4myj2rg-smYUyccm1_B-Hx9@rat-01.rmq2.cloudamqp.com:5672/qrqmckxn");

            _connection = await factory.CreateConnectionAsync();
            _channel = await _connection.CreateChannelAsync();

            await base.StartAsync(cancellationToken);


        }

        protected override async Task ExecuteAsync(CancellationToken stoppingToken)
        {
            var consumer = new AsyncEventingBasicConsumer(_channel);
    consumer.ReceivedAsync += async (ch, ea) =>
                {
                    var body = ea.Body.ToArray();
                    var message = Encoding.UTF8.GetString(body);
                    Console.WriteLine($"Mensaje recibido: {message}");
                    await _channel.BasicAckAsync(ea.DeliveryTag, false);
                };

            await _channel.BasicConsumeAsync("arso-test", false, consumer);
        }


        public override async Task StopAsync(CancellationToken cancellationToken)
        {
            await _channel.CloseAsync();
            await _connection.CloseAsync();
            await _channel.DisposeAsync();
            await _connection.DisposeAsync();
            await base.StopAsync(cancellationToken);
        }

    }


}