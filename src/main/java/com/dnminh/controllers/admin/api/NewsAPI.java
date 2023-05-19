package com.dnminh.controllers.admin.api;

import com.dnminh.models.NewsModel;
import com.dnminh.services.INewsService;
import com.dnminh.utils.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api-admin-news"})
public class NewsAPI extends HttpServlet {
    @Inject
    private INewsService newsService;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

//        NewsModel.class đại diện cho đối tượng Class<NewsModel>, NewsModel là tên lớp muốn chuyển đổi dữ liệu từ kiểu JSON thành.
/*        Class<NewsModel> newsModelClass = NewsModel.class;
        NewsModel newsModel = HttpUtil.of(req.getReader()).toModel(newsModelClass);*/
        NewsModel newsModel = HttpUtil.of(req.getReader()).toModel(NewsModel.class);
        newsModel = newsService.save(newsModel);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(resp.getOutputStream(), newsModel);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        NewsModel newsModelUpdate = HttpUtil.of(req.getReader()).toModel(NewsModel.class);
        newsModelUpdate = newsService.update(newsModelUpdate);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(resp.getOutputStream(), newsModelUpdate);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        NewsModel newsModelUpdate = HttpUtil.of(req.getReader()).toModel(NewsModel.class);
        newsService.delete(newsModelUpdate.getArrayId());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(resp.getOutputStream(), newsModelUpdate);
    }
}
