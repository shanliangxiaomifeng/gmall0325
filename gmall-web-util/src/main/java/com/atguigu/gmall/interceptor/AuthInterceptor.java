package com.atguigu.gmall.interceptor;

import com.atguigu.gmall.annotation.LoginRequire;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.handler.WebRequestHandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        //判断当前访问的方法是否需要拦截(即检查被访问的方法是否有自定义的拦截注解)
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        LoginRequire loginRequire = ((HandlerMethod) handler).getMethodAnnotation(LoginRequire.class);
        if (loginRequire == null) {
            return true;
        } else {
            response.sendRedirect("http://passport.gmall.com:8085/index");
        }
        return true;
    }
}
