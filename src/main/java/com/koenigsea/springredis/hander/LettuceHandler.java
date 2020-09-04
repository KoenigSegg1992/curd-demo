package com.koenigsea.springredis.hander;

import com.koenigsea.springredis.entity.RedisEntity;
import com.koenigsea.springredis.tools.LettuceConnector;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author KoenigSEA
 */
@Component
public class LettuceHandler {
    private final LettuceConnector lettuceConnector;

    public LettuceHandler(LettuceConnector lettuceConnector) {
        this.lettuceConnector = lettuceConnector;
    }

    public void megaInsert(List<RedisEntity> redisEntityList){
        lettuceConnector.pipelineAdd(redisEntityList);
    }
}
