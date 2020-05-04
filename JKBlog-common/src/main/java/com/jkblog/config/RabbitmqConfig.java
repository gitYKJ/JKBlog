package com.jkblog.config;


import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {
    public static final String QUEUE_NAME = "p2p-queue";
    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, true);
    }
}
