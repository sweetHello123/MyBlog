package com.it.api.handler;

import com.alibaba.fastjson.JSON;
import com.it.api.common.ErrorCode;
import com.it.api.common.Result;
import com.it.api.entity.SysUser;
import com.it.api.service.LoginService;
import com.it.api.utils.UserThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: china wu
 * @Description: 登录拦截器
 * @Date: 2022/5/14 21:24
 */
@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private LoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        // 获取token
        String token = request.getHeader("Authorization");

        log.info("=================request start===========================");
        String requestUri = request.getRequestURI();
        log.info("request uri:{}", requestUri);
        log.info("request method:{}", request.getMethod());
        log.info("token:{}", token);
        log.info("=================request end===========================");

        SysUser user = loginService.checkToken(token);
        if (user == null) {
            Result result = Result.fail(ErrorCode.NO_LOGIN.getCode(), ErrorCode.NO_LOGIN.getMsg());
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(result));
            return false;
        }
        // 登录校验成功放行
        UserThreadLocal.set(user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // ThreadLocal用完需删除，否则会有内存泄漏的风险
        UserThreadLocal.remove();
    }
}
