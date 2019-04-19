package com.hosmos.learning.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;

import java.io.Serializable;

public class ResultBody implements Serializable {
    Logger logger = Logger.getLogger(this.getClass());
    private Integer code;
    private String message;
    private Object data;

    private ResultBody(Integer code, String message, Object data){
        this.code=code;
        this.message=message;
        this.data=data;
        logger.info("ResultBody:"+ JSONObject.toJSONString(this));
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static ResultBody action(Integer code,String message,Object data){return new ResultBody(code,message,data);}

    public static ResultBody action(Integer code,String message){return new ResultBody(code,message,null);}

    public static ResultBody success(Object data){return new ResultBody(1,"success",data);}

    public static ResultBody success(String Msg,Object data){return new ResultBody(1,Msg,data);}

    public static ResultBody success(){return new ResultBody(1,"success",null);}

    public static ResultBody fail(Object data) {return new ResultBody(-1,"fail",data);}

    public static ResultBody fail() {return new ResultBody(-1,"fail",null);}
}
