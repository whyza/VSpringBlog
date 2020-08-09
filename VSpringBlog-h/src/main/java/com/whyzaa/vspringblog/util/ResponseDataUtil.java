package com.whyzaa.vspringblog.util;


public class ResponseDataUtil {

    public static ResponseData success(String msg){
        ResponseData ResponseData=new ResponseData();
        ResponseData.setCode(200);
        ResponseData.setMsg(msg);
        ResponseData.setData(null);
        return ResponseData;
    }

    public static ResponseData success(String msg,Object data){
        ResponseData ResponseData=new ResponseData();
        ResponseData.setCode(200);
        ResponseData.setMsg(msg);
        ResponseData.setData(data);
        return ResponseData;
    }

    public static ResponseData failure(Integer code,String msg){
        ResponseData ResponseData=new ResponseData();
        ResponseData.setCode(code);
        ResponseData.setMsg(msg);
        ResponseData.setData(null);
        return ResponseData;
    }

    /**
     * 无权限
     * @param msg
     * @return
     */
    public static ResponseData authorizationFailed(String msg){
        ResponseData ResponseData=new ResponseData();
        ResponseData.setCode(401);
        ResponseData.setMsg(msg);
        ResponseData.setData(null);
        return ResponseData;
    }


    public static ResponseData failure(String msg){
        ResponseData ResponseData=new ResponseData();
        ResponseData.setCode(500);
        ResponseData.setMsg(msg);
        ResponseData.setData(null);
        return ResponseData;
    }

}
