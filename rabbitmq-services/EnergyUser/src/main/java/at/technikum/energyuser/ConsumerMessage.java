package at.technikum.energyuser;

// ProducerMessage.java
public class ConsumerMessage {
    private final String type;
    private final String association;
    private final double kwh;
    private final String datetime;

    public ConsumerMessage(double kwh) {
        this.type = "USER";
        this.association = "COMMUNITY";
        this.kwh = kwh;
        this.datetime = java.time.LocalDateTime.now().toString();
    }

    @Override
    public String toString() {
        return "type:" + type + ";association:" + association + ";kwh:" + kwh + ";datetime:" + datetime;
    }
}