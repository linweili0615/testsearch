package com.example.demo.service;

import com.example.demo.config.RedisTools;
import com.example.demo.dao.SearchDao;
import com.example.demo.domain.RedisCode;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by linweili on 2018/8/24/0024.
 */
@Service
public class SearchService {

    @Autowired
    private SearchDao searchDao;

    @Autowired
    private RedisTools redisTools;

    public String getCodeByTel(String telno){
        return searchDao.getCodeByTel(telno);
    }

    public Object getCodeByInRedis(RedisCode redisCode) {
        Object code;
        switch (redisCode.getType()){
            case "string" :
                code = redisTools.getCodeByString(redisCode);
                break;
            case "hash" :
                code = redisTools.getCodeByHash(redisCode);
                break;
            case "list" :
                code = redisTools.getCodeByList(redisCode);
                break;
            case "set" :
                code = redisTools.getCodeBySet(redisCode);
                break;
            case "zset" :
                code = redisTools.getCodeByZSet(redisCode);
                break;
            default:
                code = null;
                break;
        }
        return code;
    }






}
