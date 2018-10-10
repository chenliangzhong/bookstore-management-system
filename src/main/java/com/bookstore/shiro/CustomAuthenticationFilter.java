package com.bookstore.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomAuthenticationFilter extends FormAuthenticationFilter {
    /**
     * shiro授权失败会进入此方法，判断是否是ajax请求
     * @param request
     * @param response
     * @return
     * @throws Exception
     */

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        Subject subject = getSubject(request,response);

        String uri = ((HttpServletRequest)request).getRequestURI();
        if (uri.indexOf("/api/") == 0) {
            if (subject.getPrincipal() == null) {
                request.getRequestDispatcher("/api/authc/unauthc").forward(request, response);
            }else {
                request.getRequestDispatcher("/api/authc/unperm").forward(request,response);
            }
        }else {
            return super.onAccessDenied(request,response);
        }
        return false;
    }
}
