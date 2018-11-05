package com.example.cloud.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	private Logger logger = Logger.getLogger(HelloController.class);

	@Autowired
	private DiscoveryClient client;

	@Autowired
	private Registration registration;

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String index() {

		String serviceId = registration.getServiceId();
		List<ServiceInstance> serviceInstances = client.getInstances(serviceId);
		logger.info("/hello , host:" + serviceInstances.get(0).getHost() + " ," + " service_id:" + serviceId);
		return "hello world";

	}

}
