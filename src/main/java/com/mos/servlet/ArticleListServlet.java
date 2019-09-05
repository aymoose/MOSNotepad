package com.mos.servlet;

import com.mos.db.DBManager;
import com.mos.exception.ParameterException;
import com.mos.po.Article;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class ArticleListServlet extends BaseServlet {


    @Override
    public Object process(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        System.out.println(request.getParameter("id"));

        Integer id = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<Article> articleList = new ArrayList<>();

        String sid = request.getParameter("id");

        try {
            id = Integer.parseInt(sid);
        } catch (Exception e) {
            throw new ParameterException("id错误" + sid);
        }

        try {
            connection = DBManager.getConnection();
            String sql = "select a.id,a.title,a.content,a.create_time from article a join user u on a.user_id = u.id where u.id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                //解析结果集
                Article article = new Article();
                article.setId(resultSet.getInt("id"));
                article.setTitle(resultSet.getString("title"));
                article.setContent(resultSet.getString("content"));
                //article.setUserId(resultSet.getInt("user_id"));
                article.setCreateTime(resultSet.getTimestamp("create_time"));
                articleList.add(article);
            }
        } finally {
            DBManager.close(connection, preparedStatement, resultSet);
        }
//            System.out.println(articleList);

        return articleList;
    }
}
