package com.test;

import com.config.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Resource
    public RabbitProducer producer;

    @Autowired
    private OrderService orderService;

    @Test
    public void testStringSend() {
        orderService.sendMsg();
    }

}
