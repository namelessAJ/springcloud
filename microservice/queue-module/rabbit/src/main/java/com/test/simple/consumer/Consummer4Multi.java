package com.test.simple.consumer;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.*;
import com.test.common.Constant;

import java.io.IOException;
import java.util.concurrent.*;

/**
 * @Title: Consummer4Multi
 * @date: 2020年3月11日 下午4:25:01
 */
public class Consummer4Multi {

    public static ExecutorService executorService = new ThreadPoolExecutor(4, 8, 60L, TimeUnit.SECONDS,
            new SynchronousQueue<>(), new ThreadPoolExecutor.CallerRunsPolicy());


    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        try {
            // 1.创建链接
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("127.0.0.1");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            // 2.声明队列
            channel.queueDeclare(Constant.QUEUE_NAME, true, false, false, null);

            // 3.生成消费者
            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties,
                                           byte[] body) throws IOException {
                    executorService.execute(new MessageHandleTask(new String(body, "UTF-8")));
                }
            };

            // 4.消费消息
            channel.basicConsume(Constant.QUEUE_NAME, true, consumer);

            System.out.println("耗时:" + String.valueOf(System.currentTimeMillis() - start));
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    private static class MessageHandleTask implements Runnable {

        String message;

        public MessageHandleTask(String message) {
            this.message = message;
        }

        @Override
        public void run() {
            long start = System.currentTimeMillis();
            String tName = Thread.currentThread().getName();
            System.out.println(tName + " [x] Received '" + message + "'");
            long end = System.currentTimeMillis();
            System.out.println(tName + " cost " + (end - start));
        }
    }

}
