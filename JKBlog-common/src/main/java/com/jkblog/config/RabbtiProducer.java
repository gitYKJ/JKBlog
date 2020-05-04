package com.jkblog.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbtiProducer {
    @Autowired
    private AmqpTemplate amqpTemplate;
    public void send(){
        
    }
}
