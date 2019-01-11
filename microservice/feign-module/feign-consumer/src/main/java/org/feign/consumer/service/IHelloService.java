package org.feign.consumer.service;

import org.feign.consumer.vo.HelloVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("hello-service")
public interface IHelloService {

	@RequestMapping(value = "/hello1", method = RequestMethod.GET)
	String hello(@RequestParam("name") String name);

	@RequestMapping(value = "/helloPost", method = RequestMethod.POST)
	String helloPost(HelloVO helloVO);
}
