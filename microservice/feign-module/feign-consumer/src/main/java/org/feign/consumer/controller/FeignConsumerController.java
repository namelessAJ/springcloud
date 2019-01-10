package org.feign.consumer.controller;

import java.util.Random;

import org.apache.log4j.Logger;
import org.feign.consumer.service.IHelloService;
import org.feign.consumer.vo.HelloVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignConsumerController {

	@Autowired
	IHelloService helloService;

	private Logger logger = Logger.getLogger(FeignConsumerController.class);

	@RequestMapping(value = "feign-consumerGet", method = RequestMethod.GET)
	public String feignConsumerGet(String name) throws InterruptedException {
		// 测试超时
//		int sleepTime = new Random().nextInt(3000);
//		logger.info("sleepTime :" + sleepTime);
//		Thread.sleep(sleepTime);
		return helloService.hello(name);
	}

	@RequestMapping(value = "feign-consumerPost", method = RequestMethod.POST)
	public String feignConsumerPost(@RequestBody HelloVO helloVO) {
		return helloService.helloPost(helloVO);
	}
}
