package com.example.demo.dao;

import com.example.demo.dto.CodeDTO;
import org.apache.ibatis.annotations.Param;

/**
 * Created by linweili on 2018/8/24/0024.
 */
public interface SearchDao {

    String getCodeByTel(@Param("telno") String telno);

}
