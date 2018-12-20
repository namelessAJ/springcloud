package com.test.consummer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.test.common.Constant;

/**
 * @Title: Consummer4Direct1.java
 * @date: 2018年12月19日 下午2:25:01
 */
public class Consummer4Direct1 {

	public static void main(String[] args) {

		try {

			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost("127.0.0.1");
			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();
			channel.exchangeDeclare(Constant.EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

			// 声明随机队列
			String queue = channel.queueDeclare().getQueue();

			String[] types = { "error", "info", "warning" };
			for (String type : types) {
				channel.queueBind(queue, Constant.EXCHANGE_NAME, type);
			}

			channel.basicQos(1);
			// 生成消费者
			Consumer consumer = new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties,
						byte[] body) throws IOException {

					// 获取消息内容然后处理
					String msg = new String(body, "UTF-8");
					System.out.println(" [x] Direct1==Received '" + envelope.getRoutingKey() + "':'" + msg + "'");
					channel.basicAck(envelope.getDeliveryTag(), false);
				}
			};

			// 消费消息
			channel.basicConsume(queue, false, consumer);

		} catch (IOException | TimeoutException e) {
			e.printStackTrace();
		}
	}
}
