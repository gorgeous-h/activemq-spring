import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author hjj
 * @since 2021/8/31 15:38
 */
public class JmsConsumer {
    private static final String ACTIVEMQ_URL = "tcp://192.168.111.133:61616";
    private static final String QUEUE_NAME = "queue01";

    public static void main(String[] args) throws JMSException {
        // 1、创建连接工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        // 2、通过连接工厂获取连接
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        // 3、通过连接创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 4、创建目的地
        Queue queue = session.createQueue(QUEUE_NAME);
        // 5、创建消费者
        MessageConsumer messageConsumer = session.createConsumer(queue);
        while (true) {
            TextMessage textMessage = (TextMessage) messageConsumer.receive();
            if(textMessage==null){
                break;
            } else {
                System.out.println("消费者接收到消息："+textMessage.getText());
            }
        }

        messageConsumer.close();
        session.close();
        connection.close();
    }
}
