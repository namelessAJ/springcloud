package org.ribbon.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

	@Autowired
	RestTemplate restTemplate;

	@RequestMapping(value = "/rconsumer4GetEntity", method = RequestMethod.GET)
	public String consumer4GetEntity() {
		return restTemplate.getForEntity("http://hello-service/hello?name={1}", String.class, "GetEntity").getBody();
	}

	@RequestMapping(value = "/rconsumer4GetObject", method = RequestMethod.GET)
	public String consumer4GetObject() {
		return restTemplate.getForObject("http://hello-service/hello?name={1}", String.class, "GetObject");
	}
	
	@RequestMapping(value = "/rconsumer4PostEntity", method = RequestMethod.GET)
	public String consumer4PostEntity() {
		String name = "PostEntity";
		return restTemplate.postForEntity("http://hello-service/hello", name, String.class).getBody();
	}
	
	@RequestMapping(value = "/rconsumer4PostObject", method = RequestMethod.GET)
	public String consumer4PostObject() {
		String name = "PostObject";
		return restTemplate.postForObject("http://hello-service/hello", name, String.class);
	}
}
