package com.kdm.jms.pubsub;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by Kasun Dinesh on 10-Aug-16.
 */
public class TopicProducer {
    public static void main(String[] args) throws InterruptedException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        try {
            TopicConnection connection = (TopicConnection) connectionFactory.createConnection();
            connection.setClientID("123456");
            TopicSession session = (TopicSession) connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic("test-topic1");
            connection.start();
            String msg = "Welcome to JMS";
            for (int i = 0; i < 10; i++) {
                TextMessage textMessage = session.createTextMessage(msg);
                TopicPublisher publisher = session.createPublisher(topic);
                publisher.send(textMessage);
                System.out.println("sent");
                Thread.sleep(2000);
            }
//            publisher.close();
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
