package com.htjn.assetManagement.listener;

import com.htjn.assetManagement.service.LoginAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * Created by caojy on 2018/1/7.
 * 用于监听用户鉴别失败(密码错误)事件，然后记录错误次数
 */
@Component
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    @Autowired
    private LoginAttemptService loginAttemptService;

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent e) {
        Authentication authentication = e.getAuthentication();
        String userName = (String) authentication.getPrincipal();
        loginAttemptService.loginFailed(userName);
    }
}