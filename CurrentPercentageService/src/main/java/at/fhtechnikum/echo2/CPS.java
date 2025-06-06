package at.fhtechnikum.echo2;

import at.fhtechnikum.shared.EchoMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class CPS {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private EnergyUsageRepository usageRepo;

    @Autowired
    private PercentageServiceRepository percentageRepo;

    @RabbitListener(queues = "echo.update", ackMode = "MANUAL")
    public void processText(@Payload byte[] messageBytes,
                            Channel channel,
                            @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) throws IOException {

        //Text Output
        String raw = new String(messageBytes, StandardCharsets.UTF_8);
        EchoMessage incoming = new ObjectMapper().readValue(raw, EchoMessage.class);
        System.out.println("Empfangene EchoMessage: " + incoming.getMessage());

        // Letzten Eintrag aus energy_usage holen
        EnergyUsage latest = usageRepo.findTopByOrderByHourDesc().orElse(null);
        if (latest != null) {
            percentageRepo.deleteAll(); // Alte Einträge in percentage_service löschen
            // Prozentwerte kalkulieren
            double communityDepleted = (latest.getCommunityUsed() / latest.getCommunityProduced()) * 100.0;
            double gridPortion = (latest.getGridUsed() / latest.getCommunityProduced()) * 100.0;

            // In percentage_service speichern
            PercentageServiceEntity pse = new PercentageServiceEntity();
            pse.setHour(latest.getHour());
            pse.setCommunityDepleted(communityDepleted);
            pse.setGridPortion(gridPortion);
            percentageRepo.save(pse);
        }

        System.out.println("Prozentwerte aktualisiert und gespeichert.");

        // positively acknowledge the message
        channel.basicAck(deliveryTag, false);
    }
}
