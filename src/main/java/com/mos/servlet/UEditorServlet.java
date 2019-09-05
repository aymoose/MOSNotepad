package com.mos.servlet;

import com.mos.util.MyActionEnter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with InteIIiJ IDEA.
 * Description:
 * User:
 * Date:2019-08-31
 * Time:11:04
 */
@WebServlet("/ueditor")
public class UEditorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = UEditorServlet.class.getClassLoader().getResource("/config.json").getPath();
        MyActionEnter actionEnter = new MyActionEnter(request, path);
        String exec = actionEnter.exec();
        response.getWriter().write(exec);
    }
}
