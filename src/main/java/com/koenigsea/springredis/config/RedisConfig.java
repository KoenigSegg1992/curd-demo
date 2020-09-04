package com.koenigsea.springredis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Lettuce based on Spring-Boot
 * configuration autowired into application
 * so this configuration is mostly for Jedis
 * but we can't select another redisDB if ShareNativeConnection is on
 * @author KoenigSEA
 */
@Configuration
public class RedisConfig {
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.port}")
    private Integer port;
    @Value("${spring.redis.timeout}")
    private Integer timeout;
    @Value("${spring.redis.max-wait-millis}")
    private Integer maxWaitMillis;
    @Value("${spring.redis.max-idle}")
    private Integer maxIdle;
    @Value("${spring.redis.max-total}")
    private Integer maxTotal;

    private final LettuceConnectionFactory factory;

    public RedisConfig(LettuceConnectionFactory factory) {
        this.factory = factory;
    }

    @Bean
    public JedisPool jedisPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxIdle);
        config.setMaxWaitMillis(maxWaitMillis);
        JedisPool jedisPool;
        if("".equals(password)){
            jedisPool = new JedisPool(config, host, port, timeout);
        }else{
            jedisPool = new JedisPool(config, host, port, timeout, password);
        }
        return jedisPool;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        factory.setShareNativeConnection(false);
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(factory);
        return redisTemplate;
    }

    @Override
    public String toString() {
        return "RedisConfig{" +
                "host='" + host + '\'' +
                ", password='" + password + '\'' +
                ", port=" + port +
                ", timeout=" + timeout +
                ", maxWaitMillis=" + maxWaitMillis +
                ", maxIdle=" + maxIdle +
                ", maxTotal=" + maxTotal +
                '}';
    }
}
