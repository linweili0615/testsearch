package com.example.demo.dto;

/**
 * Created by linweili on 2018/8/24/0024.
 */
public class CodeDTO {
    private Boolean status;
    private String code;
    private String msg;

    public CodeDTO(Boolean status, String code, String msg) {
        this.status = status;
        this.code = code;
        this.msg = msg;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
