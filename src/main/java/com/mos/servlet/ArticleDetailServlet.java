package com.mos.servlet;


import com.mos.db.DBManager;
import com.mos.exception.ParameterException;
import com.mos.po.Article;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/articleDetail")
public class ArticleDetailServlet extends BaseServlet {

    @Override
    public Object process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Article article = new Article();
        // 处理前端请求数据
        String sid = request.getParameter("id");
        Integer id = null;
        try {
            id = Integer.parseInt(sid);
        } catch (Exception e) {
            throw new ParameterException("id错误（" + sid + ")");
        }


        // 处理业务及数据库操作
        try {
            conn = DBManager.getConnection();
            String sql = "select id,title,content,create_time " +
                    "from article where id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                article.setId(rs.getInt("id"));
                article.setTitle(rs.getString("title"));
                article.setContent(rs.getString("content"));
                article.setCreateTime(rs.getTimestamp("create_time"));
            }
            System.out.println(article);
        } finally {
            DBManager.close(conn, ps, rs);
        }
        return article;
    }
}
