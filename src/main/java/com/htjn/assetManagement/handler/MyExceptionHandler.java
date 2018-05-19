package com.htjn.assetManagement.handler;

import com.htjn.assetManagement.util.Result;
import com.htjn.assetManagement.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by caojy on 2017/12/14.
 */

@ControllerAdvice
public class MyExceptionHandler {
    private final Logger log = LoggerFactory.getLogger(MyExceptionHandler.class);
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e){
        return ResultUtil.error(500, e.getMessage());
    }
}
