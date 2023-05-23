package com.dnminh.controllers.admin;

import com.dnminh.constant.SystemConstant;
import com.dnminh.models.NewsModel;
import com.dnminh.paging.PageRequest;
import com.dnminh.paging.Pageable;
import com.dnminh.services.INewsService;
import com.dnminh.sorting.Sorter;
import com.dnminh.utils.FormUtil;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin-news"})
public class NewsController extends HttpServlet {
    @Inject
    private INewsService newsService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NewsModel newsModel = FormUtil.toModel(NewsModel.class, req);
        /*Integer offset = (newsModel.getPage() - 1) * newsModel.getItemEachPage();*/
        Pageable pageable = new PageRequest(newsModel.getPage(), newsModel.getItemEachPage(),
                new Sorter(newsModel.getSortField(), newsModel.getSortBy()));

        newsModel.setListResult(newsService.findAll(pageable));
        newsModel.setTotalItem(newsService.getTotalItem());
        newsModel.setTotalPage((int) Math.ceil((double) newsModel.getTotalItem() / newsModel.getItemEachPage()));
        req.setAttribute(SystemConstant.MODEL, newsModel);
        RequestDispatcher rd = req.getRequestDispatcher("/views/admin/news/list.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
