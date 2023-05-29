package com.dnminh.controllers.web;

import com.dnminh.constant.SystemConstant;
import com.dnminh.models.NewsModel;
import com.dnminh.models.UserModel;
import com.dnminh.services.ICategoryService;
import com.dnminh.services.INewsService;
import com.dnminh.services.IUserService;
import com.dnminh.utils.FormUtil;
import com.dnminh.utils.SessionUtil;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

@WebServlet(urlPatterns = {"/home", "/login", "/logout"})
public class HomeController extends HttpServlet {
    @Inject
    private ICategoryService categoryService;
    @Inject
    private IUserService userService;

    ResourceBundle resourceBundle = ResourceBundle.getBundle("message");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action != null && action.equals("login")) {
            String alert = req.getParameter("alert");
            String message = req.getParameter("message");
            if (message != null && alert != null) {
                req.setAttribute("message", resourceBundle.getString(message));
                req.setAttribute("alert", alert);
            }
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/login.jsp");
            requestDispatcher.forward(req, resp);
        } else if (action != null && action.equals("logout")) {
            SessionUtil.getInstance().removeValue(req, "USERMODEL");
            resp.sendRedirect(req.getContextPath() + "/home");
        } else {
            req.setAttribute("categories", categoryService.findAll());
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/web/home.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action != null && action.equals("login")) {
            UserModel userModel = FormUtil.toModel(UserModel.class, req);
            /*userModel = userService.findByCredentials(userModel.getUsername(), userModel.getPassword(), userModel.getStatus());*/
            userModel = userService.findByCredentials(userModel.getUsername(), userModel.getPassword(), 1);
            if (userModel != null) {
                SessionUtil.getInstance().putValue(req, "USERMODEL", userModel);
                if (userModel.getRole().getCode().equals(SystemConstant.ADMIN)) {
                    resp.sendRedirect(req.getContextPath() + "/admin-home");
                } else if (userModel.getRole().getCode().equals(SystemConstant.VIEWER)) {
                    resp.sendRedirect(req.getContextPath() + "/home");
                } else {
                    resp.sendRedirect(req.getContextPath() + "/home");
                }
            } else {
                resp.sendRedirect(req.getContextPath() + "/login?action=login&message=username_password_invalid&alert=danger");
            }
        }
    }
}
