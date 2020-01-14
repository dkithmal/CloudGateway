package com.dsta.CloudGateway;

import com.dsta.CloudGatewayJMS.SQSAsyncDemo;
import com.dsta.gateway.OS3Gateway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.jms.JMSException;

@SpringBootApplication
public class CloudGatewayApplication {

	public static void main(String[] args) {

		SpringApplication.run(CloudGatewayApplication.class, args);

		//OS3Gateway gateway = new OS3Gateway();
		//gateway.init();
		//gateway.connect();

		SQSAsyncDemo sqsAsyncDemo = new SQSAsyncDemo();
		try {
			sqsAsyncDemo.runASyncQueueDemo();
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
