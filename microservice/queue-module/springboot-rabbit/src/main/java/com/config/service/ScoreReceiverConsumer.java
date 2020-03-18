package com.config.service;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.Random;

/**
 * @author aj
 */
@Service
@Slf4j
public class ScoreReceiverConsumer {

    /**
     * 积分队列
     */
    public static final String QUEUE_NAME_TRANSACTION = "api.score.queue";


    @Bean
    public Queue scoreQueue() {
        return new Queue(QUEUE_NAME_TRANSACTION, true);
    }

    @RabbitListener(queues = QUEUE_NAME_TRANSACTION)
    public void orderReceiver(Message message, @Headers Map<String, Object> headers, Channel channel) {
        try {
            log.info("message:{}", JSONObject.toJSONString(message));
            String msgBody = new String(message.getBody(), "UTF-8");
            log.info("score-message body is:{}" + msgBody);
            //幂等处理
//            if (true) {
//                //丢弃消息
//                log.info("repeat consumer ，discard the message.");
//                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
//            }
            //处理积分逻辑，插入积分数据，成功则签收消息
                //手动签收消息，通知MQ 删除此消息
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
                log.info("scoreConsumer handler success.");

        } catch (IOException e) {
            log.error("scoreConsumer handler Faild,{}", e.toString(), e);
        }
    }


}
