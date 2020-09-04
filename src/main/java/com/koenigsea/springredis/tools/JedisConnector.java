package com.koenigsea.springredis.tools;

import com.koenigsea.springredis.entity.RedisEntity;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import java.util.List;

/**
 * @author KoenigSEA
 */
@Component
public class JedisConnector {
    private final JedisPool jedisPool;

    public JedisConnector(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public void pipelineAdd(List<RedisEntity> redisEntities) {
        Jedis jedis = jedisPool.getResource();
        jedis.flushDB();
        Pipeline pipeline = jedis.pipelined();
        for (RedisEntity redisEntity : redisEntities) {
            pipeline.set(redisEntity.getKey(), redisEntity.getValue());
        }
        pipeline.sync();
    }
}
