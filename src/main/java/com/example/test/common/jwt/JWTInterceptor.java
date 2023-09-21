package com.example.test.common.jwt;

import com.example.common.util.JSONUtil;
import com.example.common.util.Result;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * JWT拦截器
 */
@Component
public class JWTInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("token");
        if (JWTUtil.verify(token)) {//token验证成功
            return true;
        } else {//token验证失败
            try {
                response.setContentType("text/html;charset=utf-8");
                PrintWriter out = response.getWriter();
                out.print(JSONUtil.getBeanToJson(Result.fail("token invalid")));
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
    }

}
