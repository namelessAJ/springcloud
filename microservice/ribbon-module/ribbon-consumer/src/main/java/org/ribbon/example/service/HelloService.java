package org.ribbon.example.service;

import org.ribbon.example.vo.HelloVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @Title: HelloService.java
 * @date: 2018年12月24日 下午4:48:27
 */
@Service
public class HelloService {

	@Autowired
	RestTemplate restTemplate;

//	@HystrixCommand(fallbackMethod = "hellFallback")
	public String helloService4GetEntity(String name) {
		return restTemplate.getForEntity("http://hello-service/helloGet?name={1}", String.class, name).getBody();
	}

//	@HystrixCommand(fallbackMethod = "hellFallback")
	public String helloService4GetObject(String name) {
		return restTemplate.getForObject("http://hello-service/helloGet?name={1}", String.class, name);
	}

	@HystrixCommand(fallbackMethod = "hellFallback")
	public String helloService4PostEntity(HelloVO helloVO) {
		return restTemplate.postForEntity("http://hello-service/helloPost", helloVO, String.class).getBody();
	}

	@HystrixCommand(fallbackMethod = "hellFallback")
	public String helloService4PostObject(HelloVO helloVO) {
		return restTemplate.postForObject("http://hello-service/helloPost", helloVO, String.class);
	}

	public String hellFallback(HelloVO helloVO) {
		return "错误了！熔断了";
	}
}
