package com.htjn.assetManagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 编码配置类
 */
@Configuration
public class EncodeConfiguration {

    /**
     * 提供全局密码加密器，可根据要求配置不同类型的加密器。
     */
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
