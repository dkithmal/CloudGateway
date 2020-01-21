package com.dsta.AWSSQSPublisher;

import com.dsta.util.Util;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

public class MainAppQueueListener implements MessageListener{

    // Defines the JNDI context factory.
    public final static String JNDI_FACTORY="weblogic.jndi.WLInitialContextFactory";

    // Defines the JMS context factory.
    public final static String JMS_FACTORY="jms/RemoteConnectionFactory";

    // Defines the queue.
    public final static String QUEUE="queue/MainIntegrationQueue";

    public void createListener(){
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
        MessageConsumer consumer = null;

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
            Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            consumer = session.createConsumer(dest);
            consumer.setMessageListener(this);

            connection.start();

        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onMessage(Message message) {
        System.out.println("onMessage from Main App Queue");

        String msgText = "";
        if (message instanceof TextMessage) {
            try {
                msgText = ((TextMessage)message).getText();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        } else {
            msgText = message.toString();
        }
        System.out.println("Message: " + msgText);

        //submit Message to AWS SQS
        AWSSQSPublisher.publishMessage(msgText,new AWSSQSMsgCompletionListenerImpl());

    }

}
