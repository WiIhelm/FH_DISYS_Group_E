
package at.fhtechnikum.producer;

import at.fhtechnikum.shared.EchoMessage;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

import java.util.Random;

public class CommunityEnergyUserMain {
    public static void main(String[] args) throws InterruptedException {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");

        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        Queue queue = new Queue("echo.usage");

        Random random = new Random();

        while (true) {
            int kWh = 5 + random.nextInt(50);

            ConsumerMessage consumerMessage = new ConsumerMessage(kWh);

            EchoMessage echoMessage = new EchoMessage(consumerMessage.toString());

            template.convertAndSend("echo.processing.exchange", queue.getName(), echoMessage);
            System.out.println("Gesendet: " + echoMessage.getMessage());

            Thread.sleep(10000);
        }
    }
}