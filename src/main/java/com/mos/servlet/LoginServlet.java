package com.mos.servlet;

import com.mos.db.DBManager;
import com.mos.po.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

/**
 * Created with InteIIiJ IDEA.
 * Description:
 * User:
 * Date:2019-08-28
 * Time:8:31
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //获取表单参数（页面中提交过来的值）
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = new User();
        user.setUserName(username);
        user.setPassWord(password);

        String sql = "select username,password from user where username=? and password=?";

        int flag = this.excuteQuery(user, sql);

        if (flag == 1) {
            //转向

            /**
             * request.getRequestDispatcher()有两种跳转：
             *
             * （1）跳转到一个servlet
             *
             *  request.getRequestDispatcher("想跳转的servlet名").forward(request, response);
             * （2）跳转到一个页面
             * request.getRequestDispatcher("文件路径").forward(request, response);
             */

            request.getRequestDispatcher("jsp/articleList.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("failure.jsp").forward(request, response);
        }

    }

    public int excuteQuery(User user, String sql) {
        int flag = 0;
        try {
            Connection connection = DBManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassWord());
            ResultSet resultSet = preparedStatement.executeQuery();
//            ResultSetMetaData resultSetMetaData = preparedStatement.getMetaData();
//
//            ResultSetMetaData rsm = resultSet.getMetaData();
            String username = null;
            String password = null;
            if (resultSet.next()) {
                username = resultSet.getString("username");
                password = resultSet.getString("password");
                if (username.equals(user.getUserName()) && password.equals(user.getPassWord())) {
                    flag = 1;
                    return flag;
                } else {
                    flag = 0;
                    return flag;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
}
