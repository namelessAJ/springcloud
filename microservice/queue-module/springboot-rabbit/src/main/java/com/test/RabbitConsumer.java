package com.test;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RabbitConsumer {

    @RabbitListener(queues = "topic_test", containerFactory = "testFactory")
    public void received(String msg, Channel channel, Message message) {
        System.out.println("[received msg=]" + msg);
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
