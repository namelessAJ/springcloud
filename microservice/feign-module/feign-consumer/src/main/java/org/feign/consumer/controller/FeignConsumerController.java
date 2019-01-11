package org.feign.consumer.controller;

import org.feign.consumer.service.IHelloService;
import org.feign.consumer.vo.HelloVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignConsumerController {

	@Autowired
	IHelloService helloService;

	@RequestMapping(value = "feign-consumerGet", method = RequestMethod.GET)
	public String feignConsumerGet(@RequestParam("name") String name) throws InterruptedException {
		return helloService.hello(name);
	}

	@RequestMapping(value = "feign-consumerPost", method = RequestMethod.POST)
	public String feignConsumerPost(@RequestBody HelloVO helloVO) {
		return helloService.helloPost(helloVO);
	}
}
