package com.test.simple.producer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import com.test.common.Constant;

/**
 * @Title: Producer.java
 * @date: 2018年12月18日 上午11:42:39
 */
public class Producer {

	public static void main(String[] args) {

		try {
			// 定义连接工厂
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost("127.0.0.1");

			// 定义connection
			Connection connection = factory.newConnection();

			// 定义channel
			Channel channel = connection.createChannel();

			// 定义队列持久化
			boolean durable = true;
			channel.queueDeclare(Constant.QUEUE_NAME, durable, false, false, null);
			for (int i = 1; i <= 10; i++) {
				String message = "hello rabbit,我是中文." + i;
				// 发布消息(MessageProperties.PERSISTENT_TEXT_PLAIN:定义消息持久化)
				channel.basicPublish("", Constant.QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN,
						message.getBytes("UTF-8"));
				System.out.println("Send:" + message);
			}

			channel.close();
			connection.close();

		} catch (IOException | TimeoutException e) {
			e.printStackTrace();
		}
	}
}
