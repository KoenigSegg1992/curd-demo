package com.koenigsea.springredis.hander;

import com.koenigsea.springredis.entity.RedisEntity;
import com.koenigsea.springredis.tools.JedisConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author KoenigSEA
 */
@Component
public class JedisHandler {
    private final JedisConnector jedisConnector;
    private final Logger logger = LoggerFactory.getLogger(JedisHandler.class);

    public JedisHandler(JedisConnector jedisConnector) {
        this.jedisConnector = jedisConnector;
    }

    public void megaInsert(List<RedisEntity> redisEntityList) {
        if (jedisConnector.pipelineAdd(redisEntityList)) {
            logger.info("Jedis Successful Inserted");
        }
    }

    public void flushAllDb() {
        jedisConnector.flushDb();
    }
}
