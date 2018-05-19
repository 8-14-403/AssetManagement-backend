package com.htjn.assetManagement.listener;

import com.htjn.assetManagement.service.LoginAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * Created by caojy on 2018/1/7.
 * 用于监听用户成功登录事件，然后从缓存中去除该用户登录失败记录
 */
@Component
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {

    @Autowired
    private LoginAttemptService loginAttemptService;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent e) {
        String username = "";
        Authentication authentication = e.getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof String) {
                username = "UNKNOWN";
            } else {
                UserDetails springSecurityUser = (UserDetails) principal;
                username = springSecurityUser.getUsername();
            }
        }
        loginAttemptService.loginSucceeded(username);
    }
}