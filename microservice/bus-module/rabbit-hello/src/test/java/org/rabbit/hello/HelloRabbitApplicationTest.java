package org.rabbit.hello;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.rabbit.hello.mq.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class HelloRabbitApplicationTest {

	@Autowired
	private Sender sender;

	@Test
	public void helloTest() {
		sender.send();
	}
}
