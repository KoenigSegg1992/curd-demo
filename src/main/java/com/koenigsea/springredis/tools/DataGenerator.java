package com.koenigsea.springredis.tools;

import com.koenigsea.springredis.entity.RedisEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author KoenigSEA
 */
@Component
public class DataGenerator {
    @Value("${generator.dataSize}")
    private Integer dataSize;

    public List<RedisEntity> dataGenerated() {
        List<RedisEntity> results = new ArrayList<>();
        for (int i = 0; i < dataSize; i++) {
            RedisEntity redisEntity = new RedisEntity();
            redisEntity.setKey(keyMaker(i));
            redisEntity.setValue(valueMaker(i));
            results.add(redisEntity);
        }
        return results;
    }

    public String keyMaker(int i) {
        return "key:" + String.format("%06d", i);
    }

    public String valueMaker(int i) {
        return "value:" + String.format("%06d", i);
    }

}
