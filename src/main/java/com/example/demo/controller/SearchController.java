package com.example.demo.controller;

import com.example.demo.domain.RedisCode;
import com.example.demo.dto.CodeDTO;
import com.example.demo.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

/**
 * Created by linweili on 2018/8/24/0024.
 */
@Controller
public class SearchController {

    private static final Logger log = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(){
        log.info("访问页面");
        return "index";
    }

    @RequestMapping(value = "/card",method = RequestMethod.GET)
    public String card(){
        return "card";
    }

    @RequestMapping(value = "/scode",method = RequestMethod.POST)
    @ResponseBody
    public CodeDTO getCodeByTel(RedisCode redisCode){
//        System.out.println(redisCode.toString());
        log.info("redisCode:{}",redisCode.toString());
        if(redisCode.getType().equals("sql") && redisCode.getKey() !=null && redisCode.getKey() != ""){
            try{
                String code = searchService.getCodeByTel(redisCode.getKey());
                log.info("code:{}",code);
                return returnMsg(code);
            }catch (NullPointerException e){
//                e.printStackTrace();
//                System.out.println("暂无此验证码信息");
                log.info("暂无此验证码信息");
                return new CodeDTO(true, "", "暂无此验证码信息");
            } catch (CannotGetJdbcConnectionException e){
                e.printStackTrace();
//                System.out.println("数据库连接超时");
                log.error("数据库连接超时");
                return new CodeDTO(true,"","数据库连接超时");
            }
        }else {
            log.error("查询参数错误");
            return new CodeDTO(false,"","查询参数错误");
        }

    }

    CodeDTO returnMsg(String code){
        if(code != null || code != ""){
            if(code.length() == 4){
                code = '0' + '0'+ code;
            }
            if(code.length() == 5){
                code = '0'+ code;
            }
            log.info("returnMsg:" + code);
            return new CodeDTO(true, code, "查询验证码成功");
        }else {
            return new CodeDTO(true, "", "暂无验证码信息");
        }
    }

    @RequestMapping(value = "/rcode",method = RequestMethod.POST)
    @ResponseBody
    public CodeDTO getCode(RedisCode redisCode){
        System.out.println(redisCode.toString());

        if(redisCode.getType().equals("string") && redisCode.getKey() != null && redisCode.getKey() != ""){
            try {
                String code = searchService.getCodeByInRedis(redisCode).toString();
                return returnMsg(code);
            }catch (NullPointerException e){
//                e.printStackTrace();
                System.out.println("暂无此验证码信息");
                return new CodeDTO(true, "", "暂无此验证码信息");
            }catch (Exception e){
                e.printStackTrace();
                return new CodeDTO(false, "", "查询验证码失败");
            }
        }else if (redisCode.getType().equals("hash") && redisCode.getHash() != null
                && redisCode.getHash() != "" && redisCode.getKey() != null && redisCode.getKey() != ""){
            try {
                    String code = searchService.getCodeByInRedis(redisCode).toString();
                    return returnMsg(code);
            }catch (NullPointerException e){
//                e.printStackTrace();
                return new CodeDTO(true, "", "暂无此验证码信息");
            } catch (Exception e){
                return new CodeDTO(false, "", "查询验证码失败");
            }
        }else {
            return new CodeDTO(false, "", "查询参数错误");
        }
    }

    @RequestMapping(value = "/target",method = RequestMethod.GET)
    @ResponseBody
    public String sub(String u){
        String keyStr = u.substring(u.length() - 4);
        Integer key = Integer.parseInt(keyStr, 16) % 32;
        return "target: "+ key;
    }



}
