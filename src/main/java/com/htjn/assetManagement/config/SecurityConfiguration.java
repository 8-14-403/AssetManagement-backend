package com.htjn.assetManagement.config;


import com.htjn.assetManagement.handler.LoginFailureHandler;
import com.htjn.assetManagement.handler.LoginSuccessHandler;
import com.htjn.assetManagement.handler.LogoutHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Spring Security配置类
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfiguration(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, PasswordEncoder passwordEncoder) {


        // 设置身份验证器
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);// 设置用户服务类
        authenticationProvider.setPasswordEncoder(passwordEncoder); // 设置密码加密方式

        // 与JDBC关联
        auth.authenticationProvider(authenticationProvider);

        // 如有需要，可自行配置与LDAP关联.
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // HTTP 请求验证逻辑
        // 以下语义为，所有请求系统任意资源(/**)的用户，都必须持有管理员权限
        // 此处请自行更改为相应安全措施
        http
                .authorizeRequests()
                    .antMatchers("/login.html").permitAll()
                    .antMatchers("/main.html").hasRole("ADMIN")
                    .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login.html").loginProcessingUrl("/login")
                            .successHandler(new LoginSuccessHandler())
                            .failureHandler(new LoginFailureHandler())
                            .usernameParameter("username")
                            .passwordParameter("password")
                            .permitAll()
                .and()
                .httpBasic().and()
                .logout().logoutUrl("/logout")
                         .logoutSuccessHandler(new LogoutHandler())
                         .permitAll()
                .and()
                .csrf().disable(); // 关闭跨站保护

    }
}
