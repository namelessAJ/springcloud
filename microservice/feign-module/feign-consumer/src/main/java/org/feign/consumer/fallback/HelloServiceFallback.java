package org.feign.consumer.fallback;

import org.feign.consumer.service.IHelloService;
import org.feign.consumer.vo.HelloVO;
import org.springframework.stereotype.Component;

@Component
public class HelloServiceFallback implements IHelloService {

	@Override
	public String hello(String name) {
		return "熔断了hello";
	}

	@Override
	public String helloPost(HelloVO helloVO) {
		return "熔断了helloPost";
	}

}
