package com.test.brocast.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @Description:
 * @date: 2019-09-18 15:42
 */
public class BroadcastProducer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("broadCastGroup");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();

        String[] tags = new String[]{"TagA", "TagB", "TagC", "TagD", "TagE", "TagF"};

        for (int i = 0; i < 1000; i++) {
            Message message = new Message("topicBroadcast", tags[i % tags.length], "order001", ("Hello world" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult result = producer.send(message);
            System.out.printf("%s%n", result);
        }
        producer.shutdown();
    }
}
