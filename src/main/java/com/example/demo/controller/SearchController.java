package com.example.demo.controller;

import com.example.demo.domain.RedisCode;
import com.example.demo.dto.CodeDTO;
import com.example.demo.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by linweili on 2018/8/24/0024.
 */
@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "/sqlserverCode",method = RequestMethod.GET)
    @ResponseBody
    public CodeDTO getCodeByTel(String telno){
        if(telno !=null && telno != ""){
            try{
                String code = searchService.getCodeByTel(telno);
                return new CodeDTO(true,code,"查询验证码成功");
            }catch (Exception e){
                return new CodeDTO(true,"","查询验证码失败");
            }
        }else {
            return new CodeDTO(false,"","手机号不能为空");
        }

    }

    @RequestMapping(value = "/redisCode",method = RequestMethod.GET)
    @ResponseBody
    public String getCode(RedisCode redisCode){
        redisCode.getType();
        return "helfef";

    }

}
