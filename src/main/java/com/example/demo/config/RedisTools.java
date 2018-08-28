package com.example.demo.config;

import com.example.demo.domain.RedisCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisTools {

    private static final Logger log = LoggerFactory.getLogger(RedisTools.class);

    @Autowired
    private RedisTemplate redisTemplate;

    public Object getCodeByString(RedisCode redisCode){
        log.info("getCodeByString:{}",redisCode);
        return  redisTemplate.opsForValue().get(redisCode.getKey());
    }

    public Object getCodeByHash(RedisCode redisCode){
        log.info("getCodeByHash:{}",redisCode);
        return redisTemplate.opsForHash().get(redisCode.getHash(), redisCode.getKey());
    }

    public Object getCodeByList(RedisCode redisCode){
        log.info("getCodeByList:{}",redisCode);
        return redisTemplate.opsForList().range(redisCode.getKey(), redisCode.getStart(), redisCode.getEnd());
    }

    public Object getCodeBySet(RedisCode redisCode){
        log.info("getCodeBySet:{}",redisCode);
        return redisTemplate.opsForSet().members(redisCode.getKey());
    }

    public Object getCodeByZSet(RedisCode redisCode){
        log.info("getCodeByZSet:{}",redisCode);
        return redisTemplate.opsForZSet().reverseRange(redisCode.getKey(), redisCode.getStart(),redisCode.getEnd());
    }


}
