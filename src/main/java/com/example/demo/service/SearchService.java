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

    public Object getCodeByTelInRedis(RedisCode redisCode) {
        Object code;
        switch (redisCode.getType()){
            case "string" :
//                System.out.println("string");
                code = redisTools.getCodeByString(redisCode);
//                System.out.println(code);
                break;
            case "hash" :
//                System.out.println("hash");
                code = redisTools.getCodeByHash(redisCode);
//                System.out.println("code:" + code);
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
        System.out.println(code);
        return code;
    }






}
