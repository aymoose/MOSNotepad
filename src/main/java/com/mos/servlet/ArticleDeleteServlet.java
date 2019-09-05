package com.mos.servlet;

import com.mos.db.DBManager;
import com.mos.exception.BusinessExceeption;
import com.mos.exception.ParameterException;

import javax.servlet.annotation.WebServlet;
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
@WebServlet("/articleDelete")
public class ArticleDeleteServlet extends BaseServlet {
    @Override
    public Object process(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String ids = request.getParameter("ids");
        int[] idsArray = null;
        try {
            String[] idArray = ids.split(",");
            idsArray = new int[idArray.length];
            for (int i = 0; i < idsArray.length; i++) {
                idsArray[i] = Integer.parseInt(idArray[i]);
            }
        } catch (Exception e) {
            throw new ParameterException("请求参数错误" + ids);
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
//        //获取JSON类型的请求数据
//        Article article = JSONUtil.get(request, Article.class);
        try {
            connection = DBManager.getConnection();
            StringBuilder sql = new StringBuilder("delete from article where id in (");
            for (int i = 0; i < idsArray.length; i++) {
                if (i == 0) {
                    sql.append("?");
                } else {
                    sql.append(",?");
                }
            }
            sql.append(")");

            System.out.println(sql);

            preparedStatement = connection.prepareStatement(sql.toString());
            for (int i = 0; i < idsArray.length; i++) {
                preparedStatement.setInt(i + 1, idsArray[i]);
            }


            int effect = preparedStatement.executeUpdate();
            if (effect > 0) {
                return effect;
            } else {
                throw new BusinessExceeption("没有该用户");//+ article.getUserAccout()
            }
        } finally {
            DBManager.close(connection, preparedStatement, null);
        }
    }
}
