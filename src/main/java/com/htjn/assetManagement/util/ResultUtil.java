package com.htjn.assetManagement.util;

import com.htjn.assetManagement.entity.ResultEnum;

/**
 * Created by caojy on 12/5/17.
 */
public class ResultUtil {

    public static Result error(int code, String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMessage(msg);
        return result;
    }

    public static Result success(ResultEnum resultEnum, Object object){
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMessage(resultEnum.getMsg());
        result.setData(object);
        return result;
     }

    public static Result error(ResultEnum resultEnum){
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMessage(resultEnum.getMsg());
        return result;
    }
}
