package org.feign.consumer.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;

@Configuration
public class FeignRibbonConfiguration {

	@Bean
	public IRule ribbonRule() {
		// 轮询策略
		return new RoundRobinRule();
	}

}
