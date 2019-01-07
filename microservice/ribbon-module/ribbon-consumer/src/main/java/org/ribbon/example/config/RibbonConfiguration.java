package org.ribbon.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

@Configuration
public class RibbonConfiguration {

	@Bean
	public IRule ribbonRule() {
		// 随机策略
		return new RandomRule();
	}

}
