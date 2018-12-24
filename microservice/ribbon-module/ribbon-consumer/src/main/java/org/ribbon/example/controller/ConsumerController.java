package org.ribbon.example.controller;

import java.net.URI;

import org.ribbon.example.service.HelloService;
import org.ribbon.example.vo.HelloVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	HelloService helloService;

	// GET请求
	@RequestMapping(value = "/rconsumer4GetEntity", method = RequestMethod.GET)
	public String consumer4GetEntity(@RequestParam String name) {
		return helloService.helloService4GetEntity(name);
	}

	@RequestMapping(value = "/rconsumer4GetObject", method = RequestMethod.GET)
	public String consumer4GetObject(@RequestParam String name) {
		return helloService.helloService4GetObject(name);
	}

	// POST请求
	@RequestMapping(value = "/rconsumer4PostEntity", method = RequestMethod.POST)
	public String consumer4PostEntity(@RequestBody HelloVO helloVO) {
		return helloService.helloService4PostEntity(helloVO);
	}

	@RequestMapping(value = "/rconsumer4PostObject", method = RequestMethod.POST)
	public String consumer4PostObject(@RequestBody HelloVO helloVO) {
		return helloService.helloService4PostObject(helloVO);
	}

	@RequestMapping(value = "/rconsumer4PostLocation", method = RequestMethod.POST)
	public String consumer4PostLocation(@RequestBody HelloVO helloVO) {
		URI uri = restTemplate.postForLocation("http://hello-service/helloPost", helloVO, String.class);
		return uri.toString();
	}

}
