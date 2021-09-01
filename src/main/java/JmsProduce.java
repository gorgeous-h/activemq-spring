import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author hjj
 * @since 2021/8/31 15:36
 */
public class JmsProduce {
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
        // 5、创建消息的生产者
        MessageProducer messageProducer = session.createProducer(queue);
        // 6、消息生产者将消息发送到队列中
        for(int i = 1; i <= 3; i++){
            // 通过会话创建消息
            TextMessage textMessage = session.createTextMessage("msg---" + i);
            messageProducer.send(textMessage);
        }
        messageProducer.close();
        session.close();
        connection.close();

        System.out.println("消息已发送到MQ中");
    }
}
