package com.base.intercepter;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Jxx on 2016/7/19.
 */
public class MyIntercepterLoginTest implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        try {//session中有登陆过的用户名，则通过
            HttpSession session = request.getSession();
            String userPhone = session.getAttribute("userPhone").toString();
            return true;// 只有返回true才会继续向下执行，返回false取消当前请求
        }
        catch (Exception e)
        {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().print("{\"msg\":\"请先登录\",\"code\":\"0\"}");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }





}
