package com.koenigsea.springredis.hander;

import com.koenigsea.springredis.entity.RedisEntity;
import com.koenigsea.springredis.tools.LettuceConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author KoenigSEA
 */
@Component
public class LettuceHandler {
    @Value("${generator.batchSize}")
    private Integer batchSize;
    private final LettuceConnector lettuceConnector;
    private final Logger logger = LoggerFactory.getLogger(LettuceHandler.class);

    public LettuceHandler(LettuceConnector lettuceConnector) {
        this.lettuceConnector = lettuceConnector;
    }

    public void megaInsert(List<RedisEntity> redisEntityList) {
        List<RedisEntity> tempList = new ArrayList<>();
        int count = 0;
        int indexBatch = 1;
        for (RedisEntity redisEntity : redisEntityList) {
            tempList.add(redisEntity);
            count++;
            if (count == batchSize) {
                if (lettuceConnector.pipelineAdd(tempList)) {
                    logger.info("Lettuce Successful Inserted Batch: {}", indexBatch);
                }
                count = 0;
                indexBatch++;
            }
        }
    }
}
