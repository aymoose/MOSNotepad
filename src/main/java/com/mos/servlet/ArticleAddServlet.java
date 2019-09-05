package com.mos.servlet;

import com.mos.db.DBManager;
import com.mos.exception.BusinessExceeption;
import com.mos.po.Article;
import com.mos.util.JSONUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created with InteIIiJ IDEA.
 * Description:
 * User:
 * Date:2019-08-29
 * Time:14:40
 */
public class ArticleAddServlet extends BaseServlet {
    @Override
    public Object process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;


        //application/json数据需要使用inputStream来获取   下面的方式获取不到
//        String userAccount = request.getParameter("userAccount");
//        String title = request.getParameter("title");
//        String content = request.getParameter("content");

        //获取JSON类型的请求数据
        Article article = JSONUtil.get(request, Article.class);
        try {
            connection = DBManager.getConnection();
            String sql = "insert into article(title,content,user_id,create_time) " +
                    "select ?,?,user.id,now() from user where user.username=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, article.getTitle());
            preparedStatement.setString(2, article.getContent());
            preparedStatement.setString(3, article.getUserAccout());

            int effect = preparedStatement.executeUpdate();
            if (effect > 0) {
                return effect;
            } else {
                throw new BusinessExceeption("没有该用户" + article.getUserAccout());
            }
        } finally {
            DBManager.close(connection, preparedStatement, null);
        }
    }
}
