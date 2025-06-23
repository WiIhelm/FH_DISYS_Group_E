
package at.fhtechnikum.producer;

import at.fhtechnikum.shared.EchoMessage;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

import java.util.Random;

public class CommunityEnergyProducerMain {
    public static void main(String[] args) throws InterruptedException {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");

        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        Queue queue = new Queue("echo.usage");

        WeatherService weatherService = new WeatherService();
        Random random = new Random();

        while (true) {
            boolean sunny = weatherService.isSunny();
            int kWh = sunny ? 80 + random.nextInt(40) : 20 + random.nextInt(40);

            ProducerMessage producerMessage = new ProducerMessage(kWh);

            EchoMessage echoMessage = new EchoMessage(producerMessage.toString());

            template.convertAndSend("echo.processing.exchange", queue.getName(), echoMessage);
            System.out.println("Gesendet: " + echoMessage.getMessage());

            Thread.sleep(10000);
        }
    }
}