package org.feign.consumer.service;

import org.feign.consumer.vo.HelloVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("hello-service")
public interface IHelloService {

	@RequestMapping("/helloGet")
	String helloGet();

	@RequestMapping("/helloPost")
	String helloPost(HelloVO helloVO);
}
