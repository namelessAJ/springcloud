package com.config;

import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class RabbitConfig {

    public static final String QUEUE = "topic_test";

    @Bean(name = "testConnectionFactory")
    @Primary
    public ConnectionFactory crmConnectionFactory(
            @Value("${spring.rabbitmq.host}") String host,
            @Value("${spring.rabbitmq.port}") int port,
            @Value("${spring.rabbitmq.username}") String username,
            @Value("${spring.rabbitmq.password}") String password,
            @Value("${spring.rabbitmq.virtual-host}") String virtualhost
    ) {

        return connectionFactory(host, port, username, password, virtualhost);
    }

    public CachingConnectionFactory connectionFactory(String host, int port, String username, String password, String virtualhost) {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        if (StringUtils.isNotEmpty(virtualhost)) {
            connectionFactory.setVirtualHost(virtualhost);
        }
        return connectionFactory;
    }

    @Bean(name = "testFactory")
    public SimpleRabbitListenerContainerFactory crmFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer, @Qualifier("testConnectionFactory") ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    @Bean
    public Queue testQueue() {
        return new Queue(QUEUE);
    }
}
