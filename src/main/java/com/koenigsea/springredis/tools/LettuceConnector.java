package com.koenigsea.springredis.tools;

import com.koenigsea.springredis.entity.RedisEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author KoenigSEA
 */
@Component
public class LettuceConnector {
    @Value("${spring.redis.sub-database:1}")
    private Integer subDatabase;

    private final StringRedisTemplate stringRedisTemplate;

    public LettuceConnector(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public boolean pipelineAdd(List<RedisEntity> redisEntities) {
        stringRedisTemplate.executePipelined((RedisCallback<Object>) redisConnection -> {
            StringRedisConnection stringRedisConn = (StringRedisConnection) redisConnection;
            stringRedisConn.select(subDatabase);
            for (RedisEntity redisEntity : redisEntities) {
                stringRedisConn.set(redisEntity.getKey(), redisEntity.getValue());
            }
            return null;
        });
        return true;
    }
}
