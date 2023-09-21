package com.example.test.common.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 跨域过滤器
 *
 * @author dougang
 * @date 2020/08/08
 */
@Component
@WebFilter("/*")
public class OriginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        String url = request.getHeader("Origin");
        if (url == null) {
            url = request.getHeader("referer");
        }
        HttpServletResponse response = (HttpServletResponse) res;
        // 设置允许跨域的路径
        response.setHeader("Access-Control-Allow-Origin", url);
        // 设置允许跨域的请求头
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,Access-Token,Appid,Secret,Authorization,token");
        // 允许 GET, POST, PUT, DELETE 方法跨域
        response.setHeader("Access-Control-Allow-Methods", "POST,GET,DELETE,PUT,OPTIONS,token");
        // 允许发送Cookie
        response.setHeader("Access-Control-Allow-Credentials", "true");
        // 允许跨域时间
        response.setHeader("Access-Control-Max-Age", "3600");
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
    }

}