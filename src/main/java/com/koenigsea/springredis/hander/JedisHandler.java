package com.koenigsea.springredis.hander;

import com.koenigsea.springredis.entity.RedisEntity;
import com.koenigsea.springredis.tools.JedisConnector;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author KoenigSEA
 */
@Component
public class JedisHandler {
    private final JedisConnector jedisConnector;

    public JedisHandler(JedisConnector jedisConnector) {
        this.jedisConnector = jedisConnector;
    }

    public void megaInsert(List<RedisEntity> redisEntityList){
        jedisConnector.pipelineAdd(redisEntityList);
    }
}
