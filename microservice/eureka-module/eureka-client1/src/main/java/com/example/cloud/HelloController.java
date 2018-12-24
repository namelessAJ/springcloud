package com.example.cloud;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cloud.vo.HelloVO;

@RestController
public class HelloController {

	private Logger logger = Logger.getLogger(HelloController.class);

	@Autowired
	private DiscoveryClient client;

	@Autowired
	private Registration registration;

	@RequestMapping(value = "/helloGet")
	public String helloGet(String name) {
		String serviceId = registration.getServiceId();
		List<ServiceInstance> serviceInstances = client.getInstances(serviceId);
		logger.info("/hello , host:" + serviceInstances.get(0).getHost() + " ," + " service_id:" + serviceId);
		return "get请求1: hello world " + name;
	}

	@RequestMapping(value = "/helloPost")
	public String helloPost(@RequestBody HelloVO helloVO) {
		String name = helloVO.getName();
		String serviceId = registration.getServiceId();
		List<ServiceInstance> serviceInstances = client.getInstances(serviceId);
		logger.info("/hello , host:" + serviceInstances.get(0).getHost() + " ," + " service_id:" + serviceId);

		return "post请求1: hello world " + name;
	}
}
