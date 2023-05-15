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

@WebServlet(urlPatterns = {"/home"})
public class HomeController extends HttpServlet {
    private static final long serialVersionUID = 2686801510274002166L;
    @Inject
    private ICategoryService categoryService;
    @Inject
    private INewsService newsService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = "Central Party Committee to cast confidence vote in Politburo, Secretariat";
        String content = "The 13th Central Party Committee will hold a vote of confidence in members of the Politburo and Secretariat at the 7th plenum.";
        Long categoryId = 1L;
        NewsModel newsModel = new NewsModel();
        newsModel.setTitle(title);
        newsModel.setContent(content);
        newsModel.setCategoryId(categoryId);
        newsService.save(newsModel);

        req.setAttribute("categories", categoryService.findAll());
        RequestDispatcher rd = req.getRequestDispatcher("/views/web/home.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
