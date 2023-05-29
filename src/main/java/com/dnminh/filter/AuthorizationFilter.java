package com.dnminh.filter;

import com.dnminh.constant.SystemConstant;
import com.dnminh.models.UserModel;
import com.dnminh.utils.SessionUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationFilter implements Filter {
    private ServletContext servletContext;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.servletContext = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String uri = request.getRequestURI();
        if (uri.startsWith("/Servlet_JSP_JDBC_war_exploded/admin")) {
            UserModel userModel = (UserModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
            if (userModel != null) {
                if (userModel.getRole().getCode().equals(SystemConstant.ADMIN)) {
                    filterChain.doFilter(servletRequest, servletResponse);
                } else if (userModel.getRole().getCode().equals(SystemConstant.VIEWER)) {
                    response.sendRedirect(request.getContextPath() + "/login?action=login&message=not_permission&alert=danger");
                } else {
                    response.sendRedirect(request.getContextPath() + "/login?action=login&message=not_permission&alert=danger");
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/login?action=login&message=not_login&alert=danger");
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
