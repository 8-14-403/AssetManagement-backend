package com.htjn.assetManagement.handler;

import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by caojy on 2018/1/4.
 * 用于登录失败时记录日志和做其他fail处理
 */

public class LoginFailureHandler implements AuthenticationFailureHandler {


    public LoginFailureHandler() {

    }

    private static final Logger log = LoggerFactory.getLogger(LoginFailureHandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        String message = "";
        if (exception instanceof BadCredentialsException || exception instanceof InternalAuthenticationServiceException) {
            message = "用户名或密码错误!";
        } else {
            message = exception.getMessage();
        }
        request.getSession(true).setAttribute("error", message);
        //这里可以做fail的处理
        Map<String, Object> map = new HashMap<>();
        map.put("code", -1);
        map.put("message", message);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.write(JSONObject.toJSONString(map));
        out.close();
    }

}
