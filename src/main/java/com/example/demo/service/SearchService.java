package com.example.demo.service;

import com.example.demo.dao.SearchDao;
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
    private RedisTemplate redisTemplate;

    public String getCodeByTel(String telno){
        return searchDao.getCodeByTel(telno);
    }

    public String getCodeByTelInRedis(int database,String telno) {


        return searchDao.getCodeByTel(telno);
    }



}
