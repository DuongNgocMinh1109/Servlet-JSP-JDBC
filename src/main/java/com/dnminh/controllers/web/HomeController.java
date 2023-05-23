package com.dnminh.controllers.web;

import com.dnminh.models.NewsModel;
import com.dnminh.services.ICategoryService;
import com.dnminh.services.INewsService;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/home", "/login"})
public class HomeController extends HttpServlet {
    @Inject
    private ICategoryService categoryService;
    @Inject
    private INewsService newsService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action != null && action.equals("login")){
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/login.jsp");
            requestDispatcher.forward(req, resp);
        } else if (action != null && action.equals("logout")) {
            System.out.println("Pending...");
        } else {
            req.setAttribute("categories", categoryService.findAll());
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/web/home.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
