package org.rwSeparation.config;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.collect.Maps;

import io.shardingsphere.shardingjdbc.api.MasterSlaveDataSourceFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableConfigurationProperties(ShardingMasterSlaveConfig.class)
@ConditionalOnProperty({ "sharding.jdbc.datasource.ds-master.url",
		"sharding.jdbc.config.masterslave.master-data-source-name" })
public class ShardingDataSourceConfig {

	@Autowired
	private ShardingMasterSlaveConfig shardingMasterSlaveConfig;

	@Bean("dataSource")
	public DataSource masterSlaveDataSource() throws SQLException {
		shardingMasterSlaveConfig.getDataSources().forEach((k, v) -> configDataSource(v));
		Map<String, DataSource> dataSourceMap = Maps.newHashMap();
		dataSourceMap.putAll(shardingMasterSlaveConfig.getDataSources());
		DataSource dataSource = MasterSlaveDataSourceFactory.createDataSource(dataSourceMap,
				shardingMasterSlaveConfig.getMasterSlaveRule(), new HashMap<String, Object>(), new Properties());
		log.info("masterSlaveDataSource config complete");
		return dataSource;
	}

	private void configDataSource(DruidDataSource druidDataSource) {
		druidDataSource.setMaxActive(20);
		druidDataSource.setInitialSize(1);
		druidDataSource.setMaxWait(60000);
		druidDataSource.setMinIdle(1);
		druidDataSource.setTimeBetweenEvictionRunsMillis(60000);
		druidDataSource.setMinEvictableIdleTimeMillis(300000);
		druidDataSource.setValidationQuery("select 'x'");
		druidDataSource.setTestWhileIdle(true);
		druidDataSource.setTestOnBorrow(false);
		druidDataSource.setTestOnReturn(false);
		druidDataSource.setPoolPreparedStatements(true);
		druidDataSource.setMaxOpenPreparedStatements(20);
		druidDataSource.setUseGlobalDataSourceStat(true);
		try {
			druidDataSource.setFilters("stat,wall,slf4j");
		} catch (SQLException e) {
			log.error("druid configuration initialization filter", e);
		}
	}

}
