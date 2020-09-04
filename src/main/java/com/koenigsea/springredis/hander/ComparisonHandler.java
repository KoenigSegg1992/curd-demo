package com.koenigsea.springredis.hander;

import com.koenigsea.springredis.entity.RedisEntity;
import com.koenigsea.springredis.tools.DataGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author KoenigSEA
 */
@Component
public class ComparisonHandler implements CommandLineRunner {
    private final DataGenerator dataGenerator;
    private final JedisHandler jedisHandler;
    private final LettuceHandler lettuceHandler;
    private final Logger logger = LoggerFactory.getLogger(ComparisonHandler.class);

    public ComparisonHandler(DataGenerator dataGenerator, JedisHandler jedisHandler, LettuceHandler lettuceHandler) {
        this.dataGenerator = dataGenerator;
        this.jedisHandler = jedisHandler;
        this.lettuceHandler = lettuceHandler;
    }

    public void megaComparison() {
        List<RedisEntity> redisEntities = dataGenerator.megaDataGenerated();
        long pointA = System.currentTimeMillis();
        jedisHandler.megaInsert(redisEntities);
        long pointB = System.currentTimeMillis();
        lettuceHandler.megaInsert(redisEntities);
        long pointC = System.currentTimeMillis();
        logger.info("[TIMER] Jedis Inserting Mega Data: " + (pointB - pointA) + "ms");
        logger.info("[TIMER] Lettuce Inserting Mega Data: " + (pointC - pointB) + "ms");
    }

    @Override
    public void run(String... args) {
        try {
            megaComparison();
        } catch (Exception e) {
            logger.error("Comparison failed:" + e);
        }
    }
}
