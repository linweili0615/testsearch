package com.example.demo.controller;

import com.example.demo.domain.RedisCode;
import com.example.demo.dto.CodeDTO;
import com.example.demo.service.SearchService;
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

    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/scode",method = RequestMethod.POST)
    @ResponseBody
    public CodeDTO getCodeByTel(RedisCode redisCode){
        System.out.println(redisCode.toString());

        if(redisCode.getType().equals("sql") && redisCode.getKey() !=null && redisCode.getKey() != ""){
            try{
                String code = searchService.getCodeByTel(redisCode.getKey());
                return returnMsg(code);
            }catch (NullPointerException e){
//                e.printStackTrace();
                System.out.println("暂无此验证码信息");
                return new CodeDTO(true, "", "暂无此验证码信息");
            } catch (CannotGetJdbcConnectionException e){
//                e.printStackTrace();
                System.out.println("数据库连接超时");
                return new CodeDTO(true,"","数据库连接超时");
            }
        }else {
            return new CodeDTO(false,"","查询参数错误");
        }

    }


    CodeDTO returnMsg(String code){
        if(code != null || code != ""){
            return new CodeDTO(true, code, "查询验证码成功");
        }else {
            return new CodeDTO(true, code, "暂无验证码信息");
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

}
