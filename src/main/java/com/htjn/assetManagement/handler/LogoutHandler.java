package com.htjn.assetManagement.handler;

import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by caojy on 2018/1/4.
 */

public class LogoutHandler implements LogoutSuccessHandler {

    private static final Logger log = LoggerFactory.getLogger(LogoutHandler.class);


    public LogoutHandler() {

    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication) throws IOException {
        //这里可以做fail的处理
        String username = StringUtils.isEmpty(authentication) ? "" : authentication.getName();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("message", "登出成功!");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.write(JSONObject.toJSONString(map));
        out.close();
    }
}
