package at.fhtechnikum.producer;

// ProducerMessage.java
public class ProducerMessage {
    private final String type;
    private final String association;
    private final double kwh;
    private final String datetime;

    public ProducerMessage(double kwh) {
        this.type = "PRODUCER";
        this.association = "COMMUNITY";
        this.kwh = kwh;
        this.datetime = java.time.LocalDateTime.now().toString();
    }

    @Override
    public String toString() {
        return "type:" + type + ";association:" + association + ";kwh:" + kwh + ";datetime:" + datetime;
    }
}