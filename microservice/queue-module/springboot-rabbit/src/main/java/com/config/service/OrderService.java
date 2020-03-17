package com.config.service;

import com.config.OrderMqConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author aj
 */
@Service
public class OrderService implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }

    public void saveOrder() {
        sendMsg();
    }


    public void sendMsg() {
        //消息封装
        Message message = MessageBuilder.withBody("我是事务信息".getBytes()).setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .setContentEncoding("utf-8").setMessageId("001").build();

        CorrelationData correlationData = new CorrelationData("001");
        rabbitTemplate.convertAndSend(OrderMqConfig.EXCHANGE_NAME_TRANSACTION,
                OrderMqConfig.ROUTE_NAME_TRANSACTION, message, correlationData);
        System.out.println("Transactional Message success.");


    }

    /**
     * Confirmation callback.
     *
     * @param correlationData correlation data for the callback.
     * @param ack             true for ack, false for nack
     * @param cause           An optional cause, for nack, when available, otherwise null.
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println("我收到消息：" + correlationData);
        if (ack) {
            System.out.println("success");
        } else {
            System.out.println("fail");
        }
    }

    /**
     * Returned message callback.
     *
     * @param message    the returned message.
     * @param replyCode  the reply code.
     * @param replyText  the reply text.
     * @param exchange   the exchange.
     * @param routingKey the routing key.
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Send message failed:" + replyCode + " " + replyText);
        rabbitTemplate.send(message);
    }
}
