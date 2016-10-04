package com.kdm.jms.ptp;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by Kasun Dinesh on 26-Jul-16.
 */
public class Consumer {
    public static void main(String[] args) {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        try {
            Connection connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("test-queue1");
            MessageConsumer consumer = session.createConsumer(destination);
            Message message = consumer.receive(1000);// Waiting time for a message
            if (message instanceof TextMessage) {
                TextMessage msg = (TextMessage) message;
                String recMsg = msg.getText();
                System.out.println("Received: " + recMsg);
            } else {
                System.out.println("Received: " + message);
            }
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
