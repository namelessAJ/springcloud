package org.rwSeparation.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.alibaba.druid.pool.DruidDataSource;

import io.shardingsphere.api.config.MasterSlaveRuleConfiguration;
import lombok.Data;

@Data
@ConfigurationProperties(prefix = "sharding.jdbc")
public class ShardingMasterSlaveConfig {

	private Map<String, DruidDataSource> dataSources = new HashMap<>();
	private MasterSlaveRuleConfiguration masterSlaveRule;
}
