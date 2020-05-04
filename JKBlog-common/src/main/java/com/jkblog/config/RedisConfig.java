package com.jkblog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

@Configuration
@PropertySource("classpath:/properties/redis.properties")
public class RedisConfig {
    @Value("${redis.nodes}")
    private String redisNodes;
    @Bean
    public JedisCluster jedisCluster() {
        Set<HostAndPort> nodes = new HashSet<>();
        //1.根据,号拆分为多个node
        String[] strNode = redisNodes.split(",");
        //IP:端口
        for (String node : strNode) {
            String host = node.split(":")[0];
            int port = Integer.parseInt(node.split(":")[1]);
            HostAndPort hostAndPort =
                    new HostAndPort(host, port);
            nodes.add(hostAndPort);
        }
        return new JedisCluster(nodes);
    }
}
