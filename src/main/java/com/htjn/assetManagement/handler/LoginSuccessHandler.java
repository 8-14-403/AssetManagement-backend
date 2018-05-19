package com.htjn.assetManagement.handler;

import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by caojy on 2018/1/4.
 * 用于security 登录成功时记录日志以及做其他处理
 */

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final Logger log = LoggerFactory.getLogger(LoginSuccessHandler.class);

    public LoginSuccessHandler() {
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        HttpSession session = request.getSession(true);
        session.removeAttribute("error");
        String username = StringUtils.isEmpty(authentication) ? "" : authentication.getName();
        //可以做其他登录成功的处理
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("username", username);
        map.put("message", "登录成功!");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.write(JSONObject.toJSONString(map));
        out.close();
    }
}
