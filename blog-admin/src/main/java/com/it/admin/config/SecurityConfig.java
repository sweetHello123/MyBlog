package com.it.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author: china wu
 * @Description: 安全配置类
 * @Date: 2022/5/17 20:52
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 开启登录认证
        http.authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/img/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/plugins/**").permitAll()
                // 自定义service 来去实现实时的权限认证
                .antMatchers("/admin/**").access("@authService.auth(request,authentication)")
                .antMatchers("/pages/**").authenticated()
                .and().formLogin()
                // 自定义的登录页面
                .loginPage("/login.html")
                // 登录处理接口
                .loginProcessingUrl("/login")
                // 定义登录时的用户名的key 默认为username
                .usernameParameter("username")
                // 定义登录时的密码key，默认是password
                .passwordParameter("password")
                .defaultSuccessUrl("/pages/main.html")
                .failureUrl("/login.html")
                // 通过 不拦截，更加前面配的路径决定，这是指和登录表单相关的接口 都通过
                .permitAll()
                // 退出登录配置
                .and().logout()
                // 退出登录接口
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login.html")
                // 退出登录的接口放行
                .permitAll()
                .and()
                .httpBasic()
                .and()
                // csrf关闭 如果自定义登录 需要关闭
                .csrf().disable()
                .headers().frameOptions().sameOrigin();
    }

    public static void main(String[] args) {
        // 加密策略 MD5 不安全 彩虹表  MD5 加盐
        String mszlu = new BCryptPasswordEncoder().encode("mszlu");
        System.out.println(mszlu);
    }

}
