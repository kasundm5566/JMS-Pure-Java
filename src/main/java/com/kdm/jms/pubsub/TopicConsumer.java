package com.kdm.jms.pubsub;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by Kasun Dinesh on 10-Aug-16.
 */
public class TopicConsumer implements MessageListener{
    public static void main(String[] args) {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        try {
            TopicConnection connection = (TopicConnection) connectionFactory.createConnection();
            connection.setClientID("1235");
            TopicSession session = (TopicSession) connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic("test-topic1");
            session.createSubscriber(topic).setMessageListener(new TopicConsumer());
            connection.start();
//            session.close();
//            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("Received: "+((TextMessage)message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
