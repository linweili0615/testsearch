package com.example.demo.config;

import com.example.demo.domain.RedisCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisTools {

    @Autowired
    private RedisTemplate redisTemplate;

    public Object getCodeByString(RedisCode redisCode){
        return  redisTemplate.opsForValue().get(redisCode.getKey());
    }

    public Object getCodeByHash(RedisCode redisCode){
        return redisTemplate.opsForHash().get(redisCode.getHash(), redisCode.getKey());
    }

    public Object getCodeByList(RedisCode redisCode){
        return redisTemplate.opsForList().range(redisCode.getKey(), redisCode.getStart(), redisCode.getEnd());
    }

    public Object getCodeBySet(RedisCode redisCode){
        return redisTemplate.opsForSet().members(redisCode.getKey());
    }

    public Object getCodeByZSet(RedisCode redisCode){
        return redisTemplate.opsForZSet().reverseRange(redisCode.getKey(), redisCode.getStart(),redisCode.getEnd());
    }


}
