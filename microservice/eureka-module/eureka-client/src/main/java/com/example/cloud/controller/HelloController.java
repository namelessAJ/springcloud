package com.example.cloud.controller;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.cloud.vo.HelloVO;
import com.example.cloud.vo.User;

@RestController
public class HelloController {

	private Logger logger = Logger.getLogger(HelloController.class);

	@Autowired
	private DiscoveryClient client;

	@Autowired
	private Registration registration;

	@RequestMapping(value = "/helloGet")
	public String helloGet(String name, HttpServletRequest req) {
		String serviceId = registration.getServiceId();
		List<ServiceInstance> serviceInstances = client.getInstances(serviceId);
		logger.info("/hello , host:" + serviceInstances.get(0).getHost() + " ," + " service_id:" + serviceId);
		return "get请求 : hello world " + name + ":" + req.getServerPort();
	}

	@RequestMapping(value = "/helloPost")
	public String helloPost(@RequestBody HelloVO helloVO) {
		String name = helloVO.getName();
		String serviceId = registration.getServiceId();
		List<ServiceInstance> serviceInstances = client.getInstances(serviceId);
		logger.info("/hello , host:" + serviceInstances.get(0).getHost() + " ," + " service_id:" + serviceId);
		return "post请求 : hello world " + name;
	}

	@RequestMapping(value = "/hello1", method = RequestMethod.GET)
	public String hello(String name) throws InterruptedException {
		// 测试超时
		int sleepTime = new Random().nextInt(3000);
		logger.info("sleepTime :" + sleepTime);
		Thread.sleep(sleepTime);
		return "Hello1 " + name;
	}

	@RequestMapping(value = "/hello2", method = RequestMethod.GET)
	public User hello(@RequestHeader String name, @RequestHeader Integer age) {
		return new User(name, age);
	}

	@RequestMapping(value = "/hello3", method = RequestMethod.POST)
	public String hello(@RequestBody User user) {
		return "Hello " + user.getName() + ", " + user.getAge();
	}
}
