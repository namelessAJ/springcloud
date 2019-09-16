package com.test;

import com.test.RabbitProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Resource
    public RabbitProducer producer;

    @Test
    public void testStringSend() {
        for (int i = 0; i < 100; i++) {
            producer.send();
        }
    }

}
