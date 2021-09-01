import org.apache.activemq.broker.BrokerService;

/**
 * @author hjj
 * @since 2021/8/31 15:39
 */
public class EmbedBroker {

    public static void main(String[] args) throws Exception {
        BrokerService brokerService = new BrokerService();
        brokerService.setUseJmx(true);
        brokerService.addConnector("tcp://localhost:61616");
        brokerService.start();
    }

}
