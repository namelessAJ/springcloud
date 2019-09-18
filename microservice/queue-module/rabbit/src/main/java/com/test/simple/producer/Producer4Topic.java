package com.test.simple.producer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.test.common.Constant;

/**
 * @Title: Producer4Topic.java
 * @date: 2018年12月20日 14:42:39
 */
public class Producer4Topic {

	public static void main(String[] args) {

		try {
			// 定义连接工厂
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost("127.0.0.1");

			// 定义connection
			Connection connection = factory.newConnection();

			// 定义channel
			Channel channel = connection.createChannel();

			// 定义exchange
			channel.exchangeDeclare(Constant.TOPIC_EXCHANGE, BuiltinExchangeType.TOPIC);

			String[] types = { "error", "info", "warning", "cron", "error.A" };
			for (String type : types) {
				String message = "I am " + type + "message:";
				channel.basicPublish(Constant.TOPIC_EXCHANGE, type, null, message.getBytes());
				System.out.println("Send " + type + ":" + message);
			}
			channel.close();
			connection.close();

		} catch (IOException | TimeoutException e) {
			e.printStackTrace();
		}
	}
}
