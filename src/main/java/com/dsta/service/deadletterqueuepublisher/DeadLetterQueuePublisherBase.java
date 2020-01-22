package com.dsta.service.deadletterqueuepublisher;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

public class DeadLetterQueuePublisherBase {


    // Defines the JNDI context factory.
    public final static String JNDI_FACTORY="weblogic.jndi.WLInitialContextFactory";

    // Defines the JMS context factory.
    public final static String JMS_FACTORY="jms/RemoteConnectionFactory";

    // Defines the queue.
    public final static String QUEUE="queue/MainDeadLetterQueue";

    private MessageProducer producer;
    private Session session;

    public void runPublisher(){

        Context jndiContext = null;
        String url = "t3://192.168.88.13:7001";

        Hashtable properties = new Hashtable();
        properties.put(Context.INITIAL_CONTEXT_FACTORY, JNDI_FACTORY);
        properties.put(Context.PROVIDER_URL, url);
        //properties.put(Context.SECURITY_PRINCIPAL, "doradusUser");
        //properties.put(Context.SECURITY_CREDENTIALS, "Passw0rd");

        ConnectionFactory connectionFactory = null;
        Destination dest = null;
        Connection connection = null;
        //MessageProducer producer = null;

        try{
            jndiContext = new InitialContext(properties);

        }catch (NamingException e){
            try{
                jndiContext = new InitialContext(properties);
            } catch (NamingException ex) {
                ex.printStackTrace();
            }
        }

        try{
            connectionFactory = (ConnectionFactory)jndiContext.lookup(JMS_FACTORY);
            dest = (Destination)jndiContext.lookup(QUEUE);
        } catch (NamingException e) {
            e.printStackTrace();
        }

        try{
            connection = connectionFactory.createConnection();
            //Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            producer = session.createProducer(dest);

            //TextMessage message = session.createTextMessage();

            //int random = (int)(Math.random() * 50 + 1);
            //message.setText("Testing Message Id:" + random);
            //producer.send(message, new MainQueueMsgCompletionListener());


        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void publishMessage(String messageStr) throws JMSException, InterruptedException {
        TextMessage message = session.createTextMessage();

        if(messageStr.isEmpty()) {
            int random = (int)(Math.random() * 50 + 1);
            messageStr = "Testing Message Id:" + random;
        }

        message.setText(messageStr);
        producer.send(message,new DeadLetterQueueMsgCompletionListener());

    }
}
