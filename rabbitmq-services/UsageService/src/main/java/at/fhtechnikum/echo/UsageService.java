package at.fhtechnikum.echo;

import at.fhtechnikum.shared.EchoMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class UsageService {

    @Autowired
    private EnergyUsageRepository repo;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private Map<String, String> parseMessageFields(String rawMessage) {
        Map<String, String> result = new HashMap<>();
        String[] parts = rawMessage.split(";");
        for (String part : parts) {
            String[] kv = part.split(":", 2);
            if (kv.length == 2) {
                result.put(kv[0], kv[1]);
            }
        }
        return result;
    }

    @RabbitListener(queues = "echo.usage", ackMode = "MANUAL")
    public void processText(@Payload byte[] messageBytes,
                            Channel channel,
                            @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) throws IOException {
        String raw = new String(messageBytes, StandardCharsets.UTF_8);

        // Extrahieren des eigentlichen Texts
        EchoMessage incoming = new ObjectMapper().readValue(raw, EchoMessage.class);

        System.out.println("Empfangene EchoMessage: " + incoming.getMessage());

        Map<String, String> fields = parseMessageFields(incoming.getMessage());

        String type = fields.get("type");
        double kwh = Double.parseDouble(fields.get("kwh"));
        LocalDateTime ts = LocalDateTime.parse(fields.get("datetime")).withMinute(0).withSecond(0).withNano(0);

        EnergyUsage usage = repo.findById(ts).orElseGet(() -> repo.save(new EnergyUsage(ts)));

        if ("PRODUCER".equalsIgnoreCase(type)) {
            usage.setCommunityProduced(usage.getCommunityProduced() + kwh);
        } else if ("USER".equalsIgnoreCase(type)) {
            usage.setCommunityUsed(usage.getCommunityUsed() + kwh);
        }

        if (usage.getCommunityUsed() > usage.getCommunityProduced()) {
            double diff = usage.getCommunityUsed() - usage.getCommunityProduced();
            usage.setCommunityUsed(usage.getCommunityProduced());
            usage.setGridUsed(usage.getGridUsed() + diff);
        }

        repo.save(usage);

        // Nachricht an die nächste Queue senden
        System.out.println("Entry updated or created");
        Queue queue = new Queue("echo.update");

        rabbitTemplate.convertAndSend("echo.processing.exchange",
                queue.getName(),
                new EchoMessage("updated"));

        // positively acknowledge the message
        channel.basicAck(deliveryTag, false);
    }
}
