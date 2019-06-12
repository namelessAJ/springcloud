package com.test;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class RabbitProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send() {
        Date date = new Date();
        String str = "test_" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        System.out.println("[send Msg=]" + str);
        amqpTemplate.convertAndSend("topic_test", str);
    }

}
